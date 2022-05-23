package com.example.myapplication23.screen.myLocation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import aop.fastcampus.part6.chapter01.screen.mylocation.MyLocationState
import aop.fastcampus.part6.chapter01.screen.mylocation.MyLocationViewModel
import com.example.myapplication23.data.db.dao.AddressHistoryDao
import com.example.myapplication23.data.entity.database.AddressHistoryEntity
import com.example.myapplication23.data.entity.location.LocationLatLngEntity
import com.example.myapplication23.data.entity.location.MapSearchInfoEntity
import com.example.myapplication23.databinding.ActivityMyLocationBinding
import com.example.myapplication23.screen.base.BaseActivity
import com.example.myapplication23.screen.home.HomeViewModel.Companion.MY_LOCATION_KEY
import com.example.myapplication23.screen.map.MapLocationSetting.MapLocationSettingActivity
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class MyLocationActivity :
    BaseActivity<MyLocationViewModel, ActivityMyLocationBinding>()//, OnMapReadyCallback
{
    lateinit var recentAddrAdapter: RecentAddrAdapter

    private val GEOCODE_URL = "http://dapi.kakao.com/v2/local/search/address.json?query="
    private val GEOCODE_USER_INFO = "2b4e5d3d2f35dd584b398978c3aca53a"

    override val viewModel by viewModel<MyLocationViewModel> {
        parametersOf(
            intent.getParcelableExtra<MapSearchInfoEntity>(MY_LOCATION_KEY)
        )
    }

    private val addressHistoryDao by inject<AddressHistoryDao>()

    override fun getViewBinding() = ActivityMyLocationBinding.inflate(layoutInflater)


    companion object {

        fun newIntent(context: Context, mapSearchInfoEntity: MapSearchInfoEntity) =
            Intent(context, MyLocationActivity::class.java).apply {
                putExtra(MY_LOCATION_KEY, mapSearchInfoEntity)
            }
    }

    private val startSearchActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val bundle = result.data?.extras

                val str = bundle?.get(MY_LOCATION_KEY).toString()

                Toast.makeText(
                    this,
                    "=> " + bundle?.get(MY_LOCATION_KEY).toString(),
                    Toast.LENGTH_SHORT
                ).show()

                Thread {
                    val obj: URL
                    //try {
                        val address: String = URLEncoder.encode(str, "UTF-8")

                        obj = URL(GEOCODE_URL + address)

                        val con: HttpURLConnection = obj.openConnection() as HttpURLConnection

                        con.setRequestMethod("GET")
                        con.setRequestProperty("Authorization", "KakaoAK " + GEOCODE_USER_INFO)
                        con.setRequestProperty("content-type", "application/json")
                        con.setDoOutput(true)
                        con.setUseCaches(false)
                        con.setDefaultUseCaches(false)

                        val data = con.inputStream.bufferedReader().readText()
                        val dataList = "[$data]"
                        val xy = Gson().fromJson(dataList, Array<Address>::class.java).toList()

                        val result = MapSearchInfoEntity(
                            xy[0].documents[0].addressName,
                            xy[0].documents[0].roadAddress.buildingName,
                            LocationLatLngEntity(
                                xy[0].documents[0].y.toDouble(),
                                xy[0].documents[0].x.toDouble()
                            )
                        )

                        intent?.putExtra(MY_LOCATION_KEY, result)
                        setResult(Activity.RESULT_OK, intent);

                    Handler(Looper.getMainLooper()).postDelayed({
                        saveRecentSearchItems(result)
                    }, 1000)


                        finish()
/*
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this, e.stackTraceToString(), Toast.LENGTH_LONG).show()
                        }
                    }
 */
                }.start()
            }
        }

    private fun saveRecentSearchItems(entity: MapSearchInfoEntity) = runBlocking {
        // TODO 받아온 주소 최근 검색 목록에 저장

        val data = AddressHistoryEntity(
            id = 0L,
            name = entity.fullAddress,
            lat = entity.locationLatLng.latitude,
            lng = entity.locationLatLng.longitude
        )

        addressHistoryDao.insertAddress(data)

        recentAddrAdapter.datas.add(data)
        recentAddrAdapter.notifyDataSetChanged()

    }

    private fun openSearchActivityForResult() {
        startSearchActivityForResult.launch(
            Intent(
                applicationContext,
                SearchAddressActivity::class.java
            )
        )
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {

                val bundle = result.data?.extras
                val result = bundle?.get("result")

                intent?.putExtra(MY_LOCATION_KEY, result as MapSearchInfoEntity)
                setResult(Activity.RESULT_OK, intent);

                saveRecentSearchItems(result as MapSearchInfoEntity)

                finish()
            }
        }

    private fun openActivityForResult() {
        startForResult.launch(
            MapLocationSettingActivity.newIntent(
                this,
                intent.getParcelableExtra(MY_LOCATION_KEY)!!
            )
        )
    }


    override fun initViews() = with(binding) {
/*         toolbar.setNavigationOnClickListener {
             onBackPressed()
         }
         confirmButton.setOnClickListener {
             viewModel.confirmSelectLocation()
         }*/

        btnSetLocation.setOnClickListener {
            openActivityForResult()
        }

        etSearch.setOnClickListener {
            openSearchActivityForResult()
        }

        ivBack.setOnClickListener {
            finish()
        }

        btnClear.setOnClickListener {
            runBlocking {
                addressHistoryDao.deleteAllAddresses()
                Toast.makeText(applicationContext, "전체 삭제 완료", Toast.LENGTH_LONG).show()
                recentAddrAdapter.clear()
                recentAddrAdapter.notifyDataSetChanged()
            }
        }

        recentAddrAdapter = RecentAddrAdapter { item ->
            //Toast.makeText(applicationContext, item, Toast.LENGTH_LONG).show()
            intent?.putExtra(MY_LOCATION_KEY, MapSearchInfoEntity(item.name, item.name, LocationLatLngEntity(item.lat, item.lng)))
            setResult(Activity.RESULT_OK, intent);

            finish()
        }
        binding.rvRecentAddr.layoutManager = LinearLayoutManager(this@MyLocationActivity, LinearLayoutManager.VERTICAL, false)
        binding.rvRecentAddr.adapter = recentAddrAdapter

        runBlocking {
            for (allAddress in addressHistoryDao.getAllAddresses()) {
                recentAddrAdapter.datas.add(allAddress)
            }
        }
    }


    override fun observeData() {
        viewModel.myLocationStateLiveData.observe(this) {
            when (it) {
                is MyLocationState.Loading -> {
                    //handleLoadingState()
                }
                is MyLocationState.Success -> {
                    //if (::map.isInitialized) {
                    //handleSuccessState(it),
                }
                is MyLocationState.Confirm -> {
                    setResult(Activity.RESULT_OK, Intent().apply {
                        putExtra(MY_LOCATION_KEY, it.mapSearchInfoEntity)
                    })
                    finish()
                }
                else -> Unit
            }
        }
    }
}
