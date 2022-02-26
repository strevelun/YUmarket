package com.example.myapplication23.screen.myLocation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import aop.fastcampus.part6.chapter01.screen.mylocation.MyLocationState
import aop.fastcampus.part6.chapter01.screen.mylocation.MyLocationViewModel
import com.example.myapplication23.data.entity.location.MapSearchInfoEntity
import com.example.myapplication23.databinding.ActivityMyLocationBinding
import com.example.myapplication23.screen.base.BaseActivity
import com.example.myapplication23.screen.home.HomeViewModel.Companion.MY_LOCATION_KEY
import com.example.myapplication23.screen.map.MapLocationSetting.MapLocationSettingActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MyLocationActivity :
    BaseActivity<MyLocationViewModel, ActivityMyLocationBinding>()//, OnMapReadyCallback
{
    override val viewModel by viewModel<MyLocationViewModel> {
        parametersOf(
            intent.getParcelableExtra<MapSearchInfoEntity>(MY_LOCATION_KEY)
        )
    }

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
                Toast.makeText(this, bundle?.get(MY_LOCATION_KEY).toString(), Toast.LENGTH_SHORT).show()
            }
        }

    private fun openSearchActivityForResult() {

        startSearchActivityForResult.launch(Intent(applicationContext, SearchAddressActivity::class.java))
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {

                val bundle = result.data?.extras
                val result = bundle?.get("result")

                intent?.putExtra(MY_LOCATION_KEY, result as MapSearchInfoEntity)
                setResult(Activity.RESULT_OK, intent);

                finish()
            }
        }

    private fun openActivityForResult() {
        startForResult.launch(MapLocationSettingActivity.newIntent(this, intent.getParcelableExtra(MY_LOCATION_KEY)!!))
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
