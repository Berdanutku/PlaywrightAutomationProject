package listeners;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class AllureListener implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context,Throwable cause){
        System.out.println("Failed: "+ context.getDisplayName());
    }
}
