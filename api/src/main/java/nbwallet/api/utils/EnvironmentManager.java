package nbwallet.api.utils;

import static nbwallet.api.enums.NBWalletEndPoints.*;

public class EnvironmentManager {

//    public static int getPort(String endpoint){
////        String normalizedEndpoint = endpoint.trim().toLowerCase();
////        int port;
////        switch (normalizedEndpoint){
////            case AUTHENTICATION:
////                port = Integer.parseInt(ApiConfigReader.getValue("identity.port"));
////                break;
////            case ACCOUNT:
////                port = Integer.parseInt(ApiConfigReader.getValue("customer.port"));
////                break;
////            case MANAGER_API:
////                port = Integer.parseInt(ApiConfigReader.getValue("manager.port"));
////                break;
////            default:
////                throw new IllegalArgumentException("Некорректный эндпоинт: " + endpoint);
////        } return port;
//    }

    public static int getPort(String endpoint){
        if(endpoint.contains("authentication")){
            return Integer.parseInt(ApiConfigReader.getValue("identity.port"));
        } else if(endpoint.contains("account") || endpoint.contains("transactions") || endpoint.contains("customers")){
            return Integer.parseInt(ApiConfigReader.getValue("customer.port"));
        } else if (endpoint.contains("manager")) {
            return Integer.parseInt(ApiConfigReader.getValue("manager.port"));
        } throw new IllegalArgumentException("Некорректный эндпоинт: " + endpoint);
    }
}
