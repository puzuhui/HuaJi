package com.mingxuan.huaji.utils.alpay;

public class PayKeys {
    //
    // 请参考 Android平台安全支付服务(msp)应用开发接口(4.2 RSA算法签名)部分，
    // 并使用压缩包中的openssl RSA密钥生成工具，生成一套RSA公私钥。
    // 这里签名时，只需要使用生成的RSA私钥。
    // Note: 为安全起见，使用RSA私钥进行签名的操作过程，应该尽量放到商家服务器端去进行。\

    //合作身份者id，以2088开头的16位纯数字 此id用来支付时快速登录
    public static final String DEFAULT_PARTNER = "2088801141585777";
    //支付宝APPID
    public static final String APPID = "2017120600411624";
    //收款支付宝账号
    public static final String DEFAULT_SELLER = "1417830085@qq.com";
    //商户私钥，自助生成，在压缩包中有openssl，用此软件生成商户的公钥和私钥，写到此处要不然服务器返回错误。公钥要传到淘宝合作账户里详情请看淘宝的sdk文档
    public static final String PRIVATE ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCEqU85LIXINzxJXgaTYBuH6ijhdwUjp38J8jUazQ9H5ww2kYsfA2wGP64nH+eGXg/y3r12dCB0RD0cPluQK8Iuiob1/6pwlNVYici6WTQyHTVqfpqqK1E5ptwDROtCbNQn30wlkp85PsCb8Cjbz4scD03cq9LDuDS6h3S86ahOg5N63i1TyV+zk+f0QVpkrnvjIIOz4tz/iW5pNZqEYyTETByVZqgJRni3pDtzNV1BNiuymqZnrdnqPQ6i820/zU/NBZUJQzSnkf+jLYGsopTUFjBfUMILD0rGtL362m68cdsmOVfQ12RBzvfSlqTzjMvr6czmWM6088EOc6bRsE87AgMBAAECggEARSW7FaIPWXzw/4zzAH5n20BCbsMK+rqjjRuicoXQITpdM1RJn7C47cjjjaEjnPYKeh1/w+5jvDaVY9NdBpjIblnmXvMUc/of+jZXWkqE2lo/LXMwSuKpQ9Olek7CEa2IKABMQIlcVQpQeveDVI+pgsa3Xv5wrXOmiFNylcV05PcPS9YEFg+mSXYCVHEqRqGdroBLuJzdT3lJFoafK5PjdsCFkbzc1XZoUZS5OWz/NlAgaGDnq2p03Dw1khjYWHHoIS9MiPzy8zGuheQDNG5HFzwChd+bpg3NuN26m9zDy5WMzLLWgGMMGhHmwwg/z/Df2TJC3qt90sMwyh3tnS0cgQKBgQDJ/njYlhRJO5kqEpdF78eFbWX6Axy9gvj0Q2Mk4Cpcz9u9EVtBt4oUYbS6oheZFo6lGJAED8baliNf9j+PZgsTKaRjU6m9voyzxZyKAW+huvBNrvl4Dyl6JFQzQ0tPxgzSKhJBGsKNXUCMJ1ZiLeY1HhklrgtSbd+A/22otlZifwKBgQCoIVjcgSBuaWDu9vtEHvXMfeW/V3/QQYVhsDI41R7jdBv8k36FPNkQ8OBvFaf0GrY5UX0GQL6WPYzHY1TxRAnvwUzydOYddYQk9YdOiZ04XN8wA6jvDXP+3j6VE7Y2hX4nEIobe7xBRXSibd3L4i7slNf/OWTkuqlEZT7j4lC9RQKBgAs3RRCJrNCwLX4HkDzC5qpC+9GZEKR5PEeb4kpu5AtJQ2NJLm8EuPcOwdFHJNZMSVljdsOy+JC0m3w9mSPmP6Etw8H8KEWv6fIle96x27f4QFWf7Jw9A0mT8NTz3J+uWJHm4iKZUMo2rlnZ6VMER28Vn8HW2ywVi76G8FjY3w0TAoGALRHDYFi8+NwGwAg1Eu25tjeJfuFVwd3Og4FX9SQsQ4y8c9iV8eF9zqzDfQOxIh6G+pZ9jU+Nx3h4ZHqcKpCymTKYDeaISR1XbxDiVItPOeyrZ5OTlYFltsycuhmunq7qQk6MtxFURar4uRH6gsz/ByxfOmuIQEhJV6xMT4o7WoECgYEAxGB3Zjekk3vlMPfHaL85WSIAxTmEiyI0Tv6DTTzyaK7JpjcXxzKL9ttspTSUaI7cAmjhzMdojt/XjqZ1OV3IOyTJ5BiEe2FGrm41f27d3qJrUYI3VpTpmkPhebMfQTubPuXSiDvAidOWLPTVp6QYdDbQpjEbsZmOBd3BVzLAexY=";
    //公钥
    public static final String PUBLIC ="";

    //微信APPID
    public static final String WXAPPID = "wxa08821893494a1a5";
}

