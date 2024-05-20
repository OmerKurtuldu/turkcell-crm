package com.turkcell.customerService.business.messages;

public class Messages {
    public static class CustomerErrors{
        public static final String IndividualCustomerShouldBeExists = "individualCustomerShouldBeExists";
        public static final String IndividualCustomerWithThisIDNumberExist ="individualCustomerWithThisIDNumberExist";
        public static final String CheckNatioanlityNo = "checkNatioanlityNo";
        public static final String CheckCustomerActive = "checkCustomerActive";
        public static final String CustomerShouldBeExists = "customerShouldBeExists";

    }

    public static class AddressErrors{
        public static final String AddressShouldBeExists = "addressShouldBeExists";

    }

    public static class CityErrors{
        public static final String CityShouldBeExists = "cityShouldBeExists";

    }


}
