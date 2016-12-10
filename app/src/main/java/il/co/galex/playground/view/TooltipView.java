package il.co.galex.playground.view;

import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

public class TooltipView extends View {

    private int backgroundColor;
    private int textColor;
    private int textSize;
    private int arrowHeight;
    private int arrowWidth;
    private String text;

    private Path path;
    private Paint fillPaint;
    private Paint textPaint;

    private Rect textBounds = new Rect();

    public TooltipView(Context context) {
        super(context);
        init(null);
    }

    public TooltipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {

        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.Tooltip, 0, 0);
            try {
                backgroundColor = a.getColor(R.styleable.Tooltip_backgroundColor, Color.WHITE);
                textColor = a.getColor(R.styleable.Tooltip_textColor, Color.BLACK);
                textSize = a.getDimensionPixelSize(R.styleable.Tooltip_textSize, 20);
                arrowHeight = a.getDimensionPixelSize(R.styleable.Tooltip_arrowHeight, 20);
                arrowWidth = a.getDimensionPixelSize(R.styleable.Tooltip_arrowWidth, 20);
                text = a.getString(R.styleable.Tooltip_text);
            } finally {
                a.recycle();
            }
        }

        path = new Path();
        fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(backgroundColor);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(textSize);
        textPaint.setTypeface(Typeface.DEFAULT);

    }

        /*fillPaint.setColor(ContextCompat.getColor(getContext(), R.color.tooltip_background_color));
        fillPaint.setStrokeJoin(Paint.Join.ROUND);
        fillPaint.setPathEffect(new CornerPathEffect(getResources().getDimensionPixelSize(R.dimen.tooltip_corner_radius)));*//*
        fillPaint.setAntiAlias(true);*/
    //}

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //int width = MeasureSpec.makeMeasureSpec(300, MeasureSpec.UNSPECIFIED);
        //final int height = MeasureSpec.makeMeasureSpec(100, MeasureSpec.UNSPECIFIED);

        int widthText = (int) textPaint.measureText(text) + getPaddingRight() + getPaddingLeft();
        int heightText = textSize + getPaddingTop() + getPaddingBottom();

        int width = MeasureSpec.makeMeasureSpec(widthText, MeasureSpec.EXACTLY);
        final int height = MeasureSpec.makeMeasureSpec(heightText + arrowHeight, MeasureSpec.EXACTLY);

       /* int maxWidth = 0;
        int maxHeight = 0;

        int horizontalMargin = getResources().getDimensionPixelSize(R.dimen.tooltip_horizontal_margin);
        int verticalMargin = getResources().getDimensionPixelSize(R.dimen.tooltip_vertical_margin);
        int arrowHeight = getResources().getDimensionPixelSize(R.dimen.tooltip_arrow_height);*/

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

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

        Paint.FontMetrics metric = textPaint.getFontMetrics();
        int textHeight = (int) Math.ceil(metric.descent - metric.ascent);
        int y = (int)(textHeight - metric.descent);

        canvas.drawText(text, getPaddingLeft(), y + arrowHeight + getPaddingTop(), textPaint);

    }

    public void hide(boolean animate){

        if(animate) animate().alpha(0f);
        else setAlpha(0);
    }

    public void showOrHide(boolean show, boolean animate){

        if(show) show(animate);
        else hide(animate);
    }

    public boolean isShowing(){
        return getAlpha() == 1;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void show(boolean animate) {

        if(animate) animate().alpha(1f);
        else setAlpha(1);
    }
}
