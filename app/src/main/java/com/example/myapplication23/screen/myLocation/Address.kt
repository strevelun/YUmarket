package com.example.myapplication23.screen.myLocation

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Address(
    @Expose
    @SerializedName("documents")
    val documents: List<Documents>,
    @Expose
    @SerializedName("meta")
    val meta: Meta
)

data class Meta(
    @Expose
    @SerializedName("is_end")
    val isEnd: Boolean,
    @Expose
    @SerializedName("pageable_count")
    val pageableCount: Int,
    @Expose
    @SerializedName("total_count")
    val totalCount: Int
)

data class Documents(
    @Expose
    @SerializedName("address")
    val address: AddressEntity,
    @Expose
    @SerializedName("address_name")
    val addressName: String,
    @Expose
    @SerializedName("address_type")
    val addressType: String,
    @Expose
    @SerializedName("road_address")
    val roadAddress: RoadAddressEntity,
    @Expose
    @SerializedName("x")
    val x: String,
    @Expose
    @SerializedName("y")
    val y: String
)

data class RoadAddressEntity(
    @Expose
    @SerializedName("address_name")
    val addressName: String,
    @Expose
    @SerializedName("building_name")
    val buildingName: String,
    @Expose
    @SerializedName("main_building_no")
    val mainBuildingNo: String,
    @Expose
    @SerializedName("region_1depth_name")
    val region1depthName: String,
    @Expose
    @SerializedName("region_2depth_name")
    val region2depthName: String,
    @Expose
    @SerializedName("region_3depth_name")
    val region3depthName: String,
    @Expose
    @SerializedName("road_name")
    val roadName: String,
    @Expose
    @SerializedName("sub_building_no")
    val subBuildingNo: String,
    @Expose
    @SerializedName("underground_yn")
    val undergroundYn: String,
    @Expose
    @SerializedName("x")
    val x: String,
    @Expose
    @SerializedName("y")
    val y: String,
    @Expose
    @SerializedName("zone_no")
    val zoneNo: String
)

data class AddressEntity(
    @Expose
    @SerializedName("address_name")
    val addressName: String,
    @Expose
    @SerializedName("b_code")
    val bCode: String,
    @Expose
    @SerializedName("h_code")
    val hCode: String,
    @Expose
    @SerializedName("main_address_no")
    val mainAddressNo: String,
    @Expose
    @SerializedName("mountain_yn")
    val mountainYn: String,
    @Expose
    @SerializedName("region_1depth_name")
    val region1depthName: String,
    @Expose
    @SerializedName("region_2depth_name")
    val region2depthName: String,
    @Expose
    @SerializedName("region_3depth_h_name")
    val region3depthHName: String,
    @Expose
    @SerializedName("region_3depth_name")
    val region3depthName: String,
    @Expose
    @SerializedName("sub_address_no")
    val subAddressNo: String,
    @Expose
    @SerializedName("x")
    val x: String,
    @Expose
    @SerializedName("y")
    val y: String
)
