package hust.hhh.mystudy.ui.progressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import hust.hhh.mystudy.R;

/**
 * Created by 98595 on 2019/2/19.
 * 详情参考：https://github.com/daimajia/NumberProgressBar
 * 学习以上链接的内容，尝试自己写一下。
 * <p>
 * 缺陷：
 * 1. 自定义属性中，缺少一些判断。
 * 如：自定义文字过大，超过了空间高度，最好能够自适应。
 */

public class NumberProgressBar extends View {

    private final static int DEFAULT_MAX_PROGRESS = 100;
    //进度条上文字后缀
    private final static String SUFFIX = "%";
    private final static int DEFAULT_TEXT_COLOR = 0XFF0000FF;
    private final static int DEFAULT_TEXT_SIZE = 30;
    private final static int DEFAULT_REACHED_HEIGHT = 30;
    private final static int DEFAULT_REACHED_COLOR = 0XFFFF0000;
    private final static int DEFAULT_UNREACHED_HEIGHT = 26;
    private final static int DEFAULT_UNREACHED_COLOR = 0X33FF0000;
    private final static int DEFAULT_CURRENT_PROGRESS = 0;

    //整个View的宽，高
    private int mWidth;
    private int mHeight;

    //进度条最大值
    private int mMaxProgress = DEFAULT_MAX_PROGRESS;

    //当前进度
    private int mCurrentProgress;

    //进度条上文字
    private String mProgressText;

    //进度条上数字大小
    private float mTextSize;

    //进度条上数字的颜色
    private int mTextColor;

    //已完成进度区域的颜色
    private int mReachedColor;

    //已完成进度区域的高度
    private float mReachedHeight;

    //未完成进度区域的颜色
    private int mUnreachedColor;

    //未完成进度区域的高度
    private float mUnreachedHeight;

    private RectF mReachedRectF;
    private RectF mUnreachedRectF;
    private float mTextX;
    private float mTextY;

    private Paint mReachedRectFPaint;
    private Paint mUnreachedRectFPaint;
    private Paint mTextPaint;

    private OnProgressBarListener mOnProgressBarListener;

    public NumberProgressBar(Context context) {
        this(context, null);
    }

    public NumberProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeArray(context, attrs);
        initRectF();
        initPaint();
        setProgress(mCurrentProgress);
    }

    private void initTypeArray(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberProgressBar);
        mTextColor = typedArray.getColor(R.styleable.NumberProgressBar_text_color,
                DEFAULT_TEXT_COLOR);
        mTextSize = typedArray.getDimension(R.styleable.NumberProgressBar_text_size,
                DEFAULT_TEXT_SIZE);
        mReachedHeight = typedArray.getDimension(R.styleable.NumberProgressBar_reached_height,
                DEFAULT_REACHED_HEIGHT);
        mReachedColor = typedArray.getColor(R.styleable.NumberProgressBar_reached_color,
                DEFAULT_REACHED_COLOR);
        mUnreachedHeight = typedArray.getDimension(R.styleable.NumberProgressBar_unreached_height,
                DEFAULT_UNREACHED_HEIGHT);
        mUnreachedColor = typedArray.getColor(R.styleable.NumberProgressBar_unreached_color,
                DEFAULT_UNREACHED_COLOR);
        mCurrentProgress = typedArray.getInt(R.styleable.NumberProgressBar_current_progress,
                DEFAULT_CURRENT_PROGRESS);
    }

    private void initRectF() {
        mReachedRectF = new RectF(0, 0, 0, 0);
        mUnreachedRectF = new RectF(0, 0, 0, 0);
    }

    private void initPaint() {
        mReachedRectFPaint = new Paint();
        mReachedRectFPaint.setColor(mReachedColor);
        mReachedRectFPaint.setAntiAlias(true);

        mUnreachedRectFPaint = new Paint();
        mUnreachedRectFPaint.setColor(mUnreachedColor);
        mUnreachedRectFPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setColor(mTextColor);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec, true), measure(heightMeasureSpec, false));
    }

    /**
     * 该方法处理wrap_content时View的宽高
     */
    private int measure(int measureSpec, boolean isWidth) {

        //获取当前View的测量宽高与测量规格
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        int padding = isWidth ? getPaddingStart() + getPaddingEnd() : getPaddingTop() + getPaddingBottom();

        //确定默认宽或者高。这里直接使用系统推荐的最小宽高，然后加上padding
        int result = padding + (isWidth ? getSuggestedMinimumWidth() : getSuggestedMinimumHeight());

        if (specMode == MeasureSpec.EXACTLY) {
            //对应xml文件中match_parent和具体的数值
            result = specSize;
        } else {
            // 对应xml文件中的wrap_content
            result = Math.min(result, specSize);
        }
        return result;
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return super.getSuggestedMinimumWidth();
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return (int) Math.max((int) Math.max(mReachedHeight, mUnreachedHeight), mTextSize);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        calculateRectF();
        canvas.drawRect(mReachedRectF, mReachedRectFPaint);
        canvas.drawText(mProgressText, mTextX, mTextY, mTextPaint);
        canvas.drawRect(mUnreachedRectF, mUnreachedRectFPaint);
    }

    /**
     * 计算绘制区域
     */
    private void calculateRectF() {
        // 计算整个进度条的中间线。这里与View中间线不一样，可能存在上下padding不一致的情况
        float centerY = (mHeight - getPaddingTop() - getPaddingBottom()) / 2.0f + getPaddingTop();

        //计算已完成进度区域
        mReachedRectF.left = getPaddingStart();
        mReachedRectF.top = centerY - mReachedHeight / 2.0f;
        mReachedRectF.right = mReachedRectF.left + mCurrentProgress * 1.0f / mMaxProgress * (mWidth - getPaddingStart() - getPaddingEnd());
        mReachedRectF.bottom = centerY + mReachedHeight / 2.0f;

        //计算显示文字以及文字区域
        mProgressText = mCurrentProgress * 100 / mMaxProgress + SUFFIX;
        mTextX = mReachedRectF.right;
        //ascent是基于Baseline之上的一个推荐的间距值，descent是基于Baseline之下一个推荐的间距值，两者相加就是字体高度
        mTextY = centerY - (mTextPaint.ascent() + mTextPaint.descent()) / 2.0f;
        float textWidth = mTextPaint.measureText(mProgressText);
        if (mTextX + textWidth >= mWidth - getPaddingEnd()) {
            //防止文字越界，超过进度条显示范围
            mTextX = mWidth - getPaddingEnd() - textWidth;
        }

        //计算未完成进度区域
        mUnreachedRectF.left = mTextX + textWidth;
        mUnreachedRectF.top = centerY - mUnreachedHeight / 2.0f;
        mUnreachedRectF.right = mWidth - getPaddingEnd();
        mUnreachedRectF.bottom = centerY + mUnreachedHeight / 2.0f;
        if (mUnreachedRectF.left >= mUnreachedRectF.right) {
            //防止未完成区域绘制越界
            mUnreachedRectF.left = mUnreachedRectF.right;
        }
    }

    /**
     * 进度条在原有基础上增长或者下降
     *
     * @param increment 负数表示进度下降，正数表示上升
     */
    public void increaseProgress(int increment) {
        mCurrentProgress += increment;
        if (mCurrentProgress >= mMaxProgress) {
            mCurrentProgress = mMaxProgress;
        }
        if (mCurrentProgress <= 0) {
            mCurrentProgress = 0;
        }
        if (mOnProgressBarListener != null) {
            mOnProgressBarListener.onProgressChange(mCurrentProgress);
        }
        invalidate();
    }

    public void setProgress(int progress) {
        if (progress <= 0) {
            progress = 0;
        }
        if (progress >= mMaxProgress) {
            progress = mMaxProgress;
        }
        mCurrentProgress = progress;
        if (mOnProgressBarListener != null) {
            mOnProgressBarListener.onProgressChange(mCurrentProgress);
        }
        invalidate();
    }

    public int getProgress() {
        return mCurrentProgress;
    }

    public void setOnProgressBarListener(OnProgressBarListener listener) {
        mOnProgressBarListener = listener;
    }

    public OnProgressBarListener getOnProgressBarListener() {
        return mOnProgressBarListener;
    }

    /**
     * 创建接口，用于监听当前ProgressBar的进度
     */
    public interface OnProgressBarListener {
        void onProgressChange(int progress);
    }
}
