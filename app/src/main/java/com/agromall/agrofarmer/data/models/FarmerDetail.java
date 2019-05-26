package com.agromall.agrofarmer.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "farmer_data")
public class FarmerDetail {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("occupation")
    private String occupation;

    @SerializedName("reg_no")
    private String regNo;

    @SerializedName("gender")
    private String gender;

    @SerializedName("city")
    private String city;

    @SerializedName("lga")
    private String lga;

    @SerializedName("mobile_no")
    private String mobileNo;

    @SerializedName("passport_photo")
    private String passportPhoto;

    @SerializedName("spouse_name")
    private String spouseName;

    @SerializedName("id_no")
    private String idNo;

    @SerializedName("issue_date")
    private String issueDate;

    @SerializedName("farmer_id")
    private String farmerId;

    @SerializedName("surname")
    private String surname;

    @SerializedName("id_image")
    private String idImage;

    @SerializedName("fingerprint")
    private String fingerprint;

    @SerializedName("id_type")
    private String idType;

    @SerializedName("bvn")
    private String bvn;

    @SerializedName("state")
    private String state;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("address")
    private String address;

    @SerializedName("expiry_date")
    private String expiryDate;

    @SerializedName("middle_name")
    private String middleName;

    @SerializedName("marital_status")
    private String maritalStatus;

    @SerializedName("email_address")
    private String emailAddress;

    @SerializedName("nationality")
    private String nationality;

    @SerializedName("dob")
    private String dob;

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setLga(String lga) {
        this.lga = lga;
    }

    public String getLga() {
        return lga;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setPassportPhoto(String passportPhoto) {
        this.passportPhoto = passportPhoto;
    }

    public String getPassportPhoto() {
        return passportPhoto;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public String getIdImage() {
        return idImage;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdType() {
        return idType;
    }

    public void setBvn(String bvn) {
        this.bvn = bvn;
    }

    public String getBvn() {
        return bvn;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDob() {
        return dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FarmerDetail(int id, String occupation, String regNo, String gender, String city, String lga, String mobileNo, String passportPhoto, String spouseName, String idNo, String issueDate, String farmerId, String surname, String idImage, String fingerprint, String idType, String bvn, String state, String firstName, String address, String expiryDate, String middleName, String maritalStatus, String emailAddress, String nationality, String dob) {
        this.id = id;
        this.occupation = occupation;
        this.regNo = regNo;
        this.gender = gender;
        this.city = city;
        this.lga = lga;
        this.mobileNo = mobileNo;
        this.passportPhoto = passportPhoto;
        this.spouseName = spouseName;
        this.idNo = idNo;
        this.issueDate = issueDate;
        this.farmerId = farmerId;
        this.surname = surname;
        this.idImage = idImage;
        this.fingerprint = fingerprint;
        this.idType = idType;
        this.bvn = bvn;
        this.state = state;
        this.firstName = firstName;
        this.address = address;
        this.expiryDate = expiryDate;
        this.middleName = middleName;
        this.maritalStatus = maritalStatus;
        this.emailAddress = emailAddress;
        this.nationality = nationality;
        this.dob = dob;
    }

    @Ignore
    public FarmerDetail(String occupation, String regNo, String gender, String city, String lga, String mobileNo, String passportPhoto, String spouseName, String idNo, String issueDate, String farmerId, String surname, String idImage, String fingerprint, String idType, String bvn, String state, String firstName, String address, String expiryDate, String middleName, String maritalStatus, String emailAddress, String nationality, String dob) {
        this.occupation = occupation;
        this.regNo = regNo;
        this.gender = gender;
        this.city = city;
        this.lga = lga;
        this.mobileNo = mobileNo;
        this.passportPhoto = passportPhoto;
        this.spouseName = spouseName;
        this.idNo = idNo;
        this.issueDate = issueDate;
        this.farmerId = farmerId;
        this.surname = surname;
        this.idImage = idImage;
        this.fingerprint = fingerprint;
        this.idType = idType;
        this.bvn = bvn;
        this.state = state;
        this.firstName = firstName;
        this.address = address;
        this.expiryDate = expiryDate;
        this.middleName = middleName;
        this.maritalStatus = maritalStatus;
        this.emailAddress = emailAddress;
        this.nationality = nationality;
        this.dob = dob;
    }
}
