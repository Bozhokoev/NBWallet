package nbwallet.api.environmentmanager;

import java.util.HashMap;
import java.util.Map;

import static nbwallet.api.enums.NBWalletEndPoints.ACCOUNT;
import static nbwallet.api.enums.NBWalletEndPoints.AUTHENTICATION;
import static nbwallet.api.enums.NBWalletEndPoints.CUSTOMERS;
import static nbwallet.api.enums.NBWalletEndPoints.MANAGER_API;
import static nbwallet.api.enums.NBWalletEndPoints.TRANSACTIONS;

public class EndpointFactory {
    private static final Map<String, EndpointHandler> handlerMap = new HashMap<>();

    static {
        handlerMap.put(AUTHENTICATION, new AuthenticationHandler());
        handlerMap.put(ACCOUNT, new CustomerHandler());
        handlerMap.put(CUSTOMERS, new CustomerHandler());
        handlerMap.put(TRANSACTIONS, new CustomerHandler());
    }

    public static EndpointHandler getHandler(String endPoint) {
        if (endPoint.contains(MANAGER_API)) {
            return new ManagerApiHandler();
        }
        for (Map.Entry<String, EndpointHandler> entry : handlerMap.entrySet()) {
            if (endPoint.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        throw new IllegalArgumentException("Некорректный эндпоинт: " + endPoint);
    }
}

