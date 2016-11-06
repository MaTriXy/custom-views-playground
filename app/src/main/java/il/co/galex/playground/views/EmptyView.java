package il.co.galex.playground.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static android.content.ContentValues.TAG;

// declaration of a simple custom view
public class EmptyView extends View {
    // used when instancing a view programmatically
    public EmptyView(Context context) {
        super(context);
        Log.d(TAG, "EmptyView() called with: context");
    }
    // used when used in xml (without a style)
    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "EmptyView() called with: context, attrs");
    }
    // used when used in xml (with a a defined style)
    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "EmptyView() called with: context, attrs, defStyleAttr");
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.d(TAG, "EmptyView() called with: context = [" + context + "], attrs = [" + attrs + "], defStyleAttr = [" + defStyleAttr + "], defStyleRes = [" + defStyleRes + "]");
    }
}
