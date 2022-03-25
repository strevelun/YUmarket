package com.example.myapplication23.screen.map.MapLocationSetting

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.myapplication23.data.entity.location.LocationLatLngEntity
import com.example.myapplication23.data.entity.location.MapSearchInfoEntity
import com.example.myapplication23.databinding.ActivityMapLocationSettingBinding
import com.example.myapplication23.screen.MainViewModel
import com.example.myapplication23.screen.base.BaseActivity
import com.example.myapplication23.screen.home.HomeViewModel
import com.example.myapplication23.screen.home.HomeViewModel.Companion.MY_LOCATION_KEY
import com.skt.Tmap.TMapData
import com.skt.Tmap.TMapPoint
import com.skt.Tmap.TMapView
import kotlinx.coroutines.*
import org.koin.android.viewmodel.compat.SharedViewModelCompat.sharedViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel


// 핀을 끌어서 옮길때마다 위치 정보 받아서 주소 보여줌

class MapLocationSettingActivity : BaseActivity<MapLocationSettingViewModel, ActivityMapLocationSettingBinding>(){

    var isCurAddressNull = true
    private lateinit var tMapView: TMapView
    private lateinit var tMapData: TMapData

    private lateinit var tMapPoint : TMapPoint

    override val viewModel by viewModel<MapLocationSettingViewModel>()
    private val mainViewModel by viewModel<MainViewModel>()

    override fun getViewBinding() = ActivityMapLocationSettingBinding.inflate(layoutInflater)

    companion object {
        const val CAMERA_ZOOM_LEVEL = 15f

        fun newIntent(context: Context, mapSearchInfoEntity: MapSearchInfoEntity) =
            Intent(context, MapLocationSettingActivity::class.java).apply {
                putExtra(MY_LOCATION_KEY, mapSearchInfoEntity)
            }
    }

    override fun initViews(){

        initMap()

        binding.btnSetCurLocation.setOnClickListener {
            
            // 처음 들어와서 바로 설정버튼 누르면 에러

            // 이전 위치와 다른 경우에만 finish(). 같으면 아직 viewModel 갱신이 안된 상태이기 때문에 ㄱㄷ이라고 토스트
            // 먼저 지도 위치를 드래그 해서 이동시키는 즉시 isCurAddressNull = true
            
            // 로딩 이미지를 출력해서 지도 이동해서 갱신중일때 터치 막기

            if(!isCurAddressNull) {

                val entity = viewModel.getMapSearchInfo()

                val intent = Intent()
                intent.putExtra("result", entity)

                setResult(Activity.RESULT_OK, intent);
                Toast.makeText(this@MapLocationSettingActivity, "설정완료!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this@MapLocationSettingActivity, "위치를 드래그해서 갱신해주세요!", Toast.LENGTH_SHORT).show()
            }

        }

        binding.fbtnBack.setOnClickListener {
            // 위치 정보 없음 상태에서 누르면 x

            //setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    private fun initMap() = with(binding) {
        val tmapLayout = TMap
        tMapView = TMapView(applicationContext)
        tMapView.setSKTMapApiKey("l7xx47edb2787b5040fc8e004c19e85c0053")
        tmapLayout.addView(tMapView)

        val entity = intent.getParcelableExtra<MapSearchInfoEntity>(HomeViewModel.MY_LOCATION_KEY)


        // 화면을 이동하면서 화면 중앙에 해당하는 실제 좌표 위치를 tMapPoint에 계속 저장함
        // 뒤로가기를 하면 그냥 뷰모델에 반영없이 뒤로가기 하는거고
        // 이 위치로 주소설정을 하면 뷰모델에 반영

        //val lon = mainViewModel.getMapSearchInfo()?.locationLatLng!!.longitude
        //val lat = mainViewModel.getMapSearchInfo()?.locationLatLng!!.latitude


        //viewModel.getReverseGeoInformation(LocationLatLngEntity(lat, lon))

        //val t = viewModel.getMapSearchInfo()

        //if(t != null)
          //  viewModel.MapLocationSettingStateLiveData.value = MapLocationSettingState.Success(
            //    t
            //)

        binding.tvCurAddress.text = entity?.fullAddress ?: "정보없음"

        tMapView.setOnDisableScrollWithZoomLevelListener { fl, tMapPoint ->

            isCurAddressNull = true

            val job = viewModel.getReverseGeoInformation(LocationLatLngEntity(tMapPoint.latitude, tMapPoint.longitude))

            val t = viewModel.getMapSearchInfo()

            if(job.isCompleted){
                if(t != null)
                viewModel.MapLocationSettingStateLiveData.postValue(
                    MapLocationSettingState.Success(
                        t
                    )
                )
            }
        }


        val curLocation = mainViewModel.getMapSearchInfo()?.locationLatLng

        tMapView.setLocationPoint(curLocation!!.longitude, curLocation!!.latitude)
        tMapView.setCenterPoint(curLocation!!.longitude, curLocation!!.latitude)
    }

    override fun observeData() = with(viewModel) {
        viewModel.MapLocationSettingStateLiveData.observe(this@MapLocationSettingActivity) {
            when (it) {
                is MapLocationSettingState.Success -> {
                    binding.tvCurAddress.text = it.mapSearchInfoEntity.fullAddress
                    isCurAddressNull = false
                }
            }
        }
    }
}
