package com.turkcell.catalogService.business.messages;

public class Messages {

    public static class CategoryErrors{
        public static final String CategoryShouldBeExist = "categoryShouldBeExist";
        public static final String CategoryNameCanNotBeDuplicated = "categoryNameCanNotBeDuplicated";
    }

    public static class ProductErrors{
        public static final String FeatureNameCanNotRepeat = "featureNameCanNotRepeat";
        public static final String ProductShouldBeExist = "productShouldBeExist";
    }

    public static class FeatureErrors{
        public static  final String FeatureNameCanNotBeDuplicated = "featureNameCanNotBeDuplicated";
        public static final String FeatureShouldBeExist = "featureShouldBeExist";
    }
}
