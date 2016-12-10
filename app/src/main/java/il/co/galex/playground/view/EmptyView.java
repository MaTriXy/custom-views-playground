package il.co.galex.playground.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static android.content.ContentValues.TAG;

// declaration of a simple custom view
public class EmptyView extends View {

    // called when instancing a view programmatically
    public EmptyView(Context context) {
        super(context);
        Log.d(TAG, "EmptyView() called with: context");
    }
    // called when added in xml
    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "EmptyView() called with: context, attrs");
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
