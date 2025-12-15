package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Generic Proxy class for service classes
 * This proxy provides logging, performance monitoring, and access control
 */
public class ServiceProxy implements InvocationHandler {
    
    private static final Logger logger = Logger.getLogger(ServiceProxy.class.getName());
    
    private Object target;
    
    public ServiceProxy(Object target) {
        this.target = target;
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.currentTimeMillis();
        
        // Log method entry
        logger.info("Entering method: " + method.getName() + " on " + target.getClass().getSimpleName());
        
        try {
            // Check for authorization (simplified example)
            if (!isAuthorized(method)) {
                throw new SecurityException("Unauthorized access to method: " + method.getName());
            }
            
            // Invoke the actual method
            Object result = method.invoke(target, args);
            
            // Log successful completion
            long endTime = System.currentTimeMillis();
            logger.info("Method " + method.getName() + " completed successfully in " + (endTime - startTime) + " ms");
            
            return result;
        } catch (Exception e) {
            // Log exceptions
            logger.log(Level.SEVERE, "Exception in method " + method.getName() + ": " + e.getMessage(), e);
            throw e;
        }
    }
    
    /**
     * Simple authorization check
     * In a real application, this would check user roles and permissions
     */
    private boolean isAuthorized(Method method) {
        // For demonstration, we'll allow all methods
        // In a real application, you would implement proper authorization logic
        return true;
    }
    
    /**
     * Factory method to create a proxy for any service
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(T target, Class<T> interfaceType) {
        return (T) Proxy.newProxyInstance(
            target.getClass().getClassLoader(),
            new Class[]{interfaceType},
            new ServiceProxy(target)
        );
    }
}