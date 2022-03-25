package com.example.myapplication23.screen.map

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import com.example.myapplication23.data.entity.location.LocationLatLngEntity
import com.example.myapplication23.databinding.FragmentMapBinding
import com.example.myapplication23.screen.base.BaseFragment
import com.example.myapplication23.util.LocationData
import com.example.myapplication23.util.LocationState
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import com.naver.maps.map.util.MarkerIcons
import com.skt.Tmap.*
import org.koin.android.viewmodel.ext.android.viewModel
import com.example.myapplication23.R
import android.graphics.Rect
import android.os.Handler
import android.os.Looper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.myapplication23.data.entity.location.MapSearchInfoEntity
import com.example.myapplication23.model.map.MapItemModel
import com.example.myapplication23.model.map.MapStoreModel
import com.example.myapplication23.screen.home.HomeViewModel
import com.example.myapplication23.screen.map.MapProductInfo.MapProductInfoActivity
import com.example.myapplication23.widget.adapter.MapFragmentPagerAdapter
import com.example.myapplication23.widget.adapter.listener.map.MapItemListAdapterListener
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.OverlayImage
import kotlinx.android.extensions.LayoutContainer
import java.lang.Math.round
import java.util.concurrent.Executor
import java.util.concurrent.Executors

// https://github.com/foreknowledge/my-places
// https://navermaps.github.io/android-map-sdk/guide-ko/2-1.html

class MapFragment : BaseFragment<MapViewModel, FragmentMapBinding>(), OnMapReadyCallback {
    override val viewModel by viewModel<MapViewModel>()

    private lateinit var viewPagerAdapter: MapFragmentPagerAdapter

    companion object {
        const val TAG = "MapFragment"

        fun newInstance() = MapFragment()
        private val LOCATION_PERMISSION_REQUEST_CODE = 1000

        lateinit var selectCheckBoxPosition:HashMap<Int, Int>
            private set
    }

    private var searchKeywords = mutableListOf<String>()
    private lateinit var layout: View

    private var filterCategoryOptions = mutableListOf<CheckBox>()
    private var filterCategoryChecked = mutableListOf<Boolean>()

    private lateinit var switchShowOnSale: Switch
    private lateinit var chkVisit: CheckBox
    private lateinit var chkFoodBeverage: CheckBox
    private lateinit var chkService: CheckBox
    private lateinit var chkFashionAccessories: CheckBox
    private lateinit var chkSupermarket: CheckBox
    private lateinit var chkFashionClothes: CheckBox
    private lateinit var chkEtc: CheckBox
    private lateinit var chkAll : CheckBox

    //private lateinit var tMapData: TMapData

    private lateinit var nMapView: MapView
    private var map: NaverMap? = null
    private lateinit var destMarker: Marker
    private lateinit var locationSource: FusedLocationSource
    private lateinit var geocoder: Geocoder
    private var infoWindow : InfoWindow? = null

    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener

    private lateinit var curLocation: Location
    private lateinit var destLocation: LocationLatLngEntity

    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog

    private var markers : ArrayList<Marker> = arrayListOf()

    private var isFragmentInitialized: Boolean = false

    /*

        부부식당 : 35.84276976076099, 128.75035319046196
        오빠야찜닭 : 35.8431743483104, 128.74862612604866
        커피플레이스 영남대점 : 35.84115830558572, 128.7540212700628

        서버에서 식당 정보를 받아와서 배열에 담고, repeat 안에서 배열을 돌면서 마커에 이름과 좌표를 넣는다.


    */

    // 상점마다 파는 여러 아이템
    val testStoreItems0 = arrayListOf(
        MapItemModel(0,1000, 10, 100, "너트",
            "https://kr.misumi-ec.com/linked/material/mech/SAI1/PHOTO/221005546274.jpg"),
        MapItemModel(1,10000, 20, 1000, "볼트",
            "http://www.suwonbolt.co/images/product/B-011.png"),
        MapItemModel(2,100000, 30, 10000, "야스리",
            "https://shop2.daumcdn.net/thumb/R500x500.q90/?fname=http%3A%2F%2Fshop2.daumcdn.net%2Fshophow%2Fp%2FC5093003239.jpg")
    )

    val testStoreItems1 = arrayListOf(
        MapItemModel(3,1000, 10, 100, "너트1111",
            "https://kr.misumi-ec.com/linked/material/mech/SAI1/PHOTO/221005546274.jpg"))

    val testStoreItems2 = arrayListOf(
        MapItemModel(6,1000, 10, 100, "너트2222",
            "https://kr.misumi-ec.com/linked/material/mech/SAI1/PHOTO/221005546274.jpg"),
        MapItemModel(7,10000, 20, 1000, "볼트2222",
            "http://www.suwonbolt.co/images/product/B-011.png")
    )

    val testStores = arrayListOf(
        MapStoreModel(0, "부부식당", LatLng(35.84276976076099, 128.75035319046196),10.0f, testStoreItems0),
        MapStoreModel(1, "오빠야찜닭", LatLng(35.8431743483104, 128.74862612604866),10.0f, testStoreItems1),
        MapStoreModel(2, "커피플레이스", LatLng(35.84115830558572, 128.7540212700628), 10.0f, testStoreItems2, "영남대점"),
        MapStoreModel(3, "오빠야찜닭3", LatLng(35.8421843483104, 128.74862612604866),10.0f, testStoreItems1),
        MapStoreModel(4, "오빠야찜닭4", LatLng(35.8441943483104, 128.74862612604866),10.0f, testStoreItems2),
        MapStoreModel(5, "오빠야찜닭5", LatLng(35.8451643483104, 128.74862612604866),10.0f, testStoreItems2),
        )

    override fun getViewBinding(): FragmentMapBinding =
        FragmentMapBinding.inflate(layoutInflater)

    override fun observeData() = with(viewModel) {
        // MainViewModel에서 value 수정할때마다 호출
        LocationData.locationStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is LocationState.Success -> {
                    updateLocation(it.mapSearchInfoEntity.locationLatLng)
                }
            }
        }
    }

    private fun initViewPager() = with(binding) {

        if (::viewPagerAdapter.isInitialized.not()) {

            // 상점 리스트 어댑터에 넣어주기. (각 상점들은 판매중인 아이템 목록 리스트를 가짐)
            viewPagerAdapter = MapFragmentPagerAdapter(this@MapFragment,
            object : MapItemListAdapterListener{
                override fun onClickItem(model: MapItemModel) {
                    startActivity(Intent(activity, MapProductInfoActivity::class.java).apply {
                        putExtra("model", model)
                    })
                }
            })
            viewPager2.adapter = viewPagerAdapter

            // TODO 식당 정보 삽입
            // 뷰페이저 어댑터에는 오로지 MapItemModel 배열만
            binding.viewPager2.apply {
                setPageTransformer(viewPagerAdapter.ZoomOutPageTransformer())
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if (!isFragmentInitialized) {
            locationSource = FusedLocationSource(this@MapFragment, LOCATION_PERMISSION_REQUEST_CODE)
            initMap()
            initDialog()
            initViewPager()
            isFragmentInitialized = true
        }
    }

    private fun initDialog() {
        builder = AlertDialog.Builder(requireContext())

        builder.setCancelable(false)
        val displayRectangle = Rect()
        val window = requireActivity().window
        window.decorView.getWindowVisibleDisplayFrame(displayRectangle)

        val inflater =
            requireActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        layout = inflater.inflate(R.layout.dialog_filter, null)
        layout.minimumWidth = ((displayRectangle.width() * 0.9f).toInt())
        layout.minimumHeight = ((displayRectangle.height() * 0.9f).toInt())

        chkAll = layout.findViewById(R.id.all)
        filterCategoryOptions.add(layout.findViewById(R.id.food_beverage))
        filterCategoryOptions.add(layout.findViewById(R.id.service))
        filterCategoryOptions.add(layout.findViewById(R.id.fashion_accessories))
        filterCategoryOptions.add(layout.findViewById(R.id.supermarket))
        filterCategoryOptions.add(layout.findViewById(R.id.fashion_clothes))
        filterCategoryOptions.add(layout.findViewById(R.id.etc))


        // 체크박스 누를때마다 돌면서 전부 true인지 확인하고, 전부 true이면 all의 체크 true
        for(item in filterCategoryOptions) {

            filterCategoryChecked.add(true)

            item.setOnClickListener { // checkOnListener안한 이유는 직접 터치하지 않고 체크박스의 체크를 설정할때 불필요하게 호출됨


                for (item in filterCategoryOptions)
                    if (!item.isChecked) {
                        chkAll.isChecked = false // 이떄 all 리스너 동작 -> all은 클릭 리스너로 바꿈
                        return@setOnClickListener
                    }

                chkAll.isChecked = true
            }
        }

        chkVisit = layout.findViewById(R.id.visit)
        //filterCategoryOptions.add(chkVisit.isChecked)
        switchShowOnSale = layout.findViewById(R.id.switch_only_on_sale)
        //filterCategoryOptions.add(switchShowOnSale.isChecked)

        chkAll.setOnClickListener {
            for (item in filterCategoryOptions)
                item.isChecked = chkAll.isChecked // 아이템마다 리스너 전부 동작

        }

        layout.findViewById<ImageButton>(R.id.btn_close_filter).setOnClickListener {

            // 필터 열때 저장했던 체크정보 다시 UI에 적용

            var check = true

            for(i in 0 until filterCategoryOptions.size) {
                filterCategoryOptions[i].isChecked = filterCategoryChecked[i]
                if(!filterCategoryOptions[i].isChecked)
                    check = false
            }
            chkAll.isChecked = check

            //chkVisit.isChecked = filterCategoryOptions[7]
            //switchShowOnSale.isChecked = filterCategoryOptions[8]

            dialog.dismiss()
            (layout.parent as ViewGroup).removeView(layout)
        }

        layout.findViewById<Button>(R.id.btn_filter_apply).setOnClickListener {

            var noChk = true

            for(item in filterCategoryOptions)
                if(item.isChecked) {
                    noChk = false
                    break
                }

            if (noChk)
            {
                Toast.makeText(context, "적어도 하나 이상 카테고리를 선택하도록 하자", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            for(i in 0 until filterCategoryOptions.size)
                filterCategoryChecked[i] = filterCategoryOptions[i].isChecked

            searchAround()

            dialog.dismiss()
            (layout.parent as ViewGroup).removeView(layout)
        }

        layout.findViewById<Button>(R.id.btn_filter_reset).setOnClickListener {
            // 어떤 하나의 체크박스만 체크하고 적용 -> 초기화 터치 -> x -> 필터

            for(i in 0 until filterCategoryOptions.size) {
                filterCategoryOptions[i].isChecked = true
                //filterCategoryChecked[i] = true
            }

            // checkonlistener를 안해줘서 각각의 체크박스들이 반응을 안해서 여기서 다시 해줌
            var check = true

            for(item in filterCategoryOptions)
                if(!item.isChecked) {
                    check = false // 이떄 all 리스너 동작 -> all은 클릭 리스너로 바꿈
                }

            if(check)
                chkAll.isChecked = true

            searchKeywords.clear()

            //chkVisit.isChecked = filterCategoryOptions[7]
            //switchShowOnSale.isChecked = filterCategoryOptions[8]
        }


        builder.setView(layout)
        builder.create()
    }

    override fun initViews() {
        super.initViews()


        binding.btnCurLocation.setOnClickListener {
            try {
                map?.cameraPosition =
                    CameraPosition(LatLng(curLocation.latitude, curLocation.longitude), 15.0)
            }catch(ex : Exception){
                Toast.makeText(context, "초기화 중", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnDestLocation.setOnClickListener {
            try {
            map?.cameraPosition =
                CameraPosition(LatLng(destLocation.latitude, destLocation.longitude), 15.0)
        }catch(ex : Exception){
            Toast.makeText(context, "초기화 중", Toast.LENGTH_SHORT).show()
        }
        }

        binding.btnFilter.setOnClickListener {

            for(item in filterCategoryOptions)
                filterCategoryChecked.add(item.isChecked)

            dialog = builder.show()
        }

        //tMapData = TMapData()

        binding.fbtnCloseViewPager.setOnClickListener {
            binding.viewPager2.visibility = View.GONE
            binding.fbtnCloseViewPager.visibility = View.GONE

            infoWindow?.close()
        }

        binding.btnSearchAround.setOnClickListener {
            // int nRadius : 검색 반경값. 0~33까지 설정 가능. 1은 300m를 나타내며 33의 경우는 9900m를 의미
            // val curViewPoint = tMapView.getTMapPointFromScreenPoint(0.5f, 0.5f)

/*
            var searchKeyword = StringBuilder()

            // 버튼 누를때마다 체크박스 확인해서 check된 것만 string에 추가하고 마지막에 ; 추가하기

            if (chkFoodBeverage.isChecked) {
                searchKeyword.append(resources.getString(R.string.POI_food_beverage))
            }

           오버레이 객체는 아무 스레드에서나 생성할 수 있습니다.
           그러나 오버레이의 속성은 스레드 안전성이 보장되지 않으므로 여러 스레드에서 동시에 접근해서는 안됩니다.
           특히 지도에 추가된 오버레이의 속성은 메인 스레드에서만 접근해야 하며, 그렇지 않으면 CalledFromWrongThreadException이 발생합니다.
           단, 오버레이가 지도에 추가되지 않았다면 다른 스레드에서 오버레이의 속성에 접근해도 예외가 발생하지 않습니다.

           따라서 대량의 오버레이를 다룰 경우 객체를 생성하고 초기 옵션을 지정하는 작업은 백그라운드 스레드에서 수행하고
            지도에 추가하는 작업만을 메인 스레드에서 수행하면 메인 스레드를 효율적으로 사용할 수 있습니다.
            다음은 1000개의 마커를 백그라운드 스레드에서 생성하고 속성을 지정한 후 메인 스레드에서 지도에 추가하는 예제입니다.
        */

            searchAround()
        }
    }

    private fun searchAround(){
        // TODO 지도에 있는 마커 다 없애기
        markers.forEach { marker ->
            marker.map = null
        }

        binding.viewPager2.visibility = View.GONE
        binding.fbtnCloseViewPager.visibility = View.GONE

        val executor : Executor = Executors.newCachedThreadPool()
        val handler = Handler(Looper.getMainLooper())

        executor.execute{
            // 백그라운드 쓰레드
            // 마커에 식당 이름과 위치
            markers = arrayListOf()

            var idx = 0

            // TODO 할거
            // 서버에서 내려받은 가게들을 하나씩 돌면서 좌표간의 거리를 계산하고, 거리가 일정 km 미만이면
            // repeat에서 쓸 가게 배열에 넣는다.
            // 또 필터창에 있는 가게 종류에 따라 해당하는 것만 필터링

            repeat(testStores.size) {

                // TODO TEST
                // id를 기준으로 테스트
                // 필터 순서와 가게 종류 순서 맞추기

                if(filterCategoryChecked[idx]) {

                    markers += Marker().apply {
                        position = LatLng(
                            testStores[idx].location.latitude,
                            testStores[idx].location.longitude
                        )
                        icon = MarkerIcons.BLACK
                        tag = testStores[idx].name
                        zIndex = idx
                        //isHideCollidedMarkers = true

                        // 가게 종류에 따라 마커 다르게 하기. 현재는 테스트를 위해 id에 따라서
                        when (testStores[idx].id) {
                            0L -> {
                                icon = OverlayImage.fromResource(R.drawable.marker_m)
                                iconTintColor = Color.parseColor("#46F5FF")
                            }
                            1L -> {
                                icon = OverlayImage.fromResource(R.drawable.marker_r)
                                iconTintColor = Color.parseColor("#FFCB41")
                            }
                            2L -> {
                                icon = OverlayImage.fromResource(R.drawable.marker_s)
                                iconTintColor = Color.parseColor("#886AFF")
                            }
                            3L -> {
                                icon = OverlayImage.fromResource(R.drawable.marker_e)
                                iconTintColor = Color.parseColor("#04B404")
                            }
                            4L -> {
                                icon = OverlayImage.fromResource(R.drawable.marker_f)
                                iconTintColor = Color.parseColor("#8A0886")
                            }
                            5L -> {
                                icon = OverlayImage.fromResource(R.drawable.marker_f)
                                iconTintColor = Color.parseColor("#0B2F3A")
                            }
                        }

                        setOnClickListener {
                            // idx로는 setOnClickListener에서 마커의 index를 못찾아서 고유값인 zIndex로 대체

                            if (this@MapFragment.infoWindow != null)
                                this@MapFragment.infoWindow?.close()

                            this@MapFragment.infoWindow = InfoWindow()
                            this@MapFragment.infoWindow?.adapter =
                                object : InfoWindow.DefaultTextAdapter(requireContext()) {
                                    override fun getText(infoWindow: InfoWindow): CharSequence {
                                        return infoWindow.marker?.tag as CharSequence
                                    }
                                }
                            this@MapFragment.infoWindow?.open(this)

                            // 여기서 오픈한 말풍선은 fbtnViewPager2를 클릭하면 제거

                            viewPagerAdapter.registerStore(testStores[zIndex])
                            binding.viewPager2.adapter = viewPagerAdapter
                            binding.viewPager2.visibility = View.VISIBLE
                            binding.fbtnCloseViewPager.visibility = View.VISIBLE
                            true
                        }
                    }
                }

                idx++
            }

            // TODO 마커 추가하고 나중에 삭제는
            handler.post{
                // 메인스레드
                markers.forEach { marker ->
                    marker.map = map
                }
            }


        }
    }

    @SuppressLint("MissingPermission")
    // https://community.openapi.sk.com/t/topic/12591
    private fun updateLocation(location: LocationLatLngEntity) {
        
        // 위치 업데이트 될 때마다 목적지 마커 초기화

        destLocation = location

        curLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)!!


        // 목적지 마커. 이미 존재하면 null
        if (::destMarker.isInitialized)
            destMarker.map = null


        destMarker = Marker()
        destMarker.zIndex = 111
        destMarker.icon = MarkerIcons.BLACK
        destMarker.iconTintColor = Color.parseColor("#FA295B")
        destMarker.width = 100
        destMarker.height = 125
        destMarker.position = LatLng(destLocation.latitude, destLocation.longitude)

        if (map != null)
        {
            destMarker.map = map
        }

        map?.cameraPosition =
            CameraPosition(LatLng(destLocation.latitude, destLocation.longitude), 15.0)
    }

    @SuppressLint("MissingPermission")
    private fun initMap() = with(binding) {

        nMapView = mapView
        nMapView.getMapAsync(this@MapFragment)

        locationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationListener = LocationListener { l ->
            curLocation = l
            curLocation.longitude = l.longitude
            curLocation.latitude = l.latitude
        }

        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            1000,
            1f,
            locationListener
        )
        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            1000,
            1f,
            locationListener
        )
    }

    override fun onMapReady(map: NaverMap) {

        this.map = map
        //geocoder = Geocoder(context)

        /* TODO
            geocoder.getFromLocationName()
         */

        map.locationSource = locationSource
        map.uiSettings.isLocationButtonEnabled = true
        map.uiSettings.isScaleBarEnabled = true
        map.locationTrackingMode = LocationTrackingMode.NoFollow
        map.uiSettings.isCompassEnabled = true

        try {
            map.cameraPosition =
                CameraPosition(LatLng(curLocation.latitude, curLocation.longitude), 15.0)
        }catch(ex : Exception){
            Toast.makeText(context, "위치 초기화 중", Toast.LENGTH_SHORT).show()
        }
    }

}