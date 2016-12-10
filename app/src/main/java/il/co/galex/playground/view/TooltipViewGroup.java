package il.co.galex.playground.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TooltipViewGroup extends ViewGroup {

    private TextView textView;
    private Path path;
    private Paint fillPaint;

    public TooltipViewGroup(Context context) {
        super(context);
        init();
    }

    private void init() {

        path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);

        fillPaint = new Paint();
        fillPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        fillPaint.setColor(ContextCompat.getColor(getContext(), R.color.tooltip_background_color));
        fillPaint.setStrokeJoin(Paint.Join.ROUND);    // set the join to round you want
        //fillPaint.setPathEffect(new CornerPathEffect(getResources().getDimensionPixelSize(R.dimen.tooltip_corner_radius)));
        fillPaint.setAntiAlias(true);

        textView = new TextView(getContext());
        if (Build.VERSION.SDK_INT < 23) {
            //noinspection deprecation
            textView.setTextAppearance(getContext(), R.style.TooltipText);
        } else {
            textView.setTextAppearance(R.style.TooltipText);
        }
        textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        textView.setMinWidth(getResources().getDimensionPixelSize(R.dimen.tooltip_min_width));
        textView.setMaxWidth(getResources().getDimensionPixelSize(R.dimen.tooltip_max_width));
        textView.setLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.END);

        addView(textView);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final int width = MeasureSpec.makeMeasureSpec(200, MeasureSpec.UNSPECIFIED);
        final int height = MeasureSpec.makeMeasureSpec(100, MeasureSpec.UNSPECIFIED);

        int maxWidth = 0;
        int maxHeight = 0;

        int horizontalMargin = getResources().getDimensionPixelSize(R.dimen.tooltip_horizontal_margin);
        int verticalMargin = getResources().getDimensionPixelSize(R.dimen.tooltip_vertical_margin);
        int arrowHeight = getResources().getDimensionPixelSize(R.dimen.tooltip_arrow_height);

        int children = getChildCount();
        for (int i = 0; i < children; i++) {
            View child = getChildAt(i);
            child.measure(width, height);

            maxWidth = Math.max(maxWidth, child.getMeasuredWidth() + horizontalMargin * 2);
            maxHeight = Math.max(maxHeight, child.getMeasuredHeight() + verticalMargin * 2 + arrowHeight);
        }

        setMeasuredDimension(MeasureSpec.makeMeasureSpec(maxWidth, MeasureSpec.AT_MOST), MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        int horizontalMargin = getResources().getDimensionPixelSize(R.dimen.tooltip_horizontal_margin);
        int verticalMargin = getResources().getDimensionPixelSize(R.dimen.tooltip_vertical_margin);
        int arrowHeight = getResources().getDimensionPixelSize(R.dimen.tooltip_arrow_height);

        int children = getChildCount();
        for (int i = 0; i < children; i++) {
            View child = getChildAt(i);

            int childLeft = horizontalMargin;
            int childTop = verticalMargin + arrowHeight;
            int childRight = getMeasuredWidth() - horizontalMargin;
            int childBottom = getMeasuredHeight() - horizontalMargin + arrowHeight;

            child.layout(childLeft, childTop, childRight, childBottom);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int arrowWidth = getResources().getDimensionPixelSize(R.dimen.tooltip_arrow_width);
        int arrowHeight = getResources().getDimensionPixelSize(R.dimen.tooltip_arrow_height);

        int middleWidth = width / 2;
        int middleArrowWidth = arrowWidth / 2;

        path.moveTo(0, arrowHeight);
        path.lineTo(middleWidth - middleArrowWidth, arrowHeight);
        path.lineTo(middleWidth, 0);
        path.lineTo(middleWidth + middleArrowWidth, arrowHeight);
        path.lineTo(width, arrowHeight);
        path.lineTo(width, height);
        path.lineTo(0, height);
        path.lineTo(0, arrowHeight);
        path.close();

        canvas.drawPath(path, fillPaint);
        super.dispatchDraw(canvas);
    }

    public void setText(String text) {
        if (textView != null) {
            textView.setText(text);
        }
    }


}
