package com.vs.toolsoso.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

/**
 * @author gl
 * @time 2016-03-18
 *
 */
public class ToolUtils {
	private static Toast mShowingToast;
	public static void ShowToast_Short(Context mContext, String msg) {
		try {
			if (mShowingToast == null) {
				mShowingToast = Toast.makeText(mContext.getApplicationContext(),msg,Toast.LENGTH_SHORT);
				mShowingToast.setGravity(Gravity.BOTTOM, 0, 20);
			}

			if (mShowingToast != null) {
				mShowingToast.setText(msg);
			}

			mShowingToast.show();

//			Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}


	public static void ShowToast_Long(Context mContext, String msg) {
		try {
			if (mShowingToast == null) {
				mShowingToast = Toast.makeText(mContext.getApplicationContext(),msg,Toast.LENGTH_LONG);
				mShowingToast.setGravity(Gravity.BOTTOM, 0, 20);

			}
			if (mShowingToast != null) {
				mShowingToast.setText(msg);
			}
			mShowingToast.show();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	/**
	 * 取消Toast的显示
	 */
	public static void cancelToast(){

		if (mShowingToast != null) {
			mShowingToast.cancel();
		}
	}

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 屏幕变暗
	 */
	public static void setScreenDark(Context mContext) {
		WindowManager.LayoutParams lp = ((Activity) mContext).getWindow()
				.getAttributes();
		lp.alpha = 0.4f;
		((Activity) mContext).getWindow().setAttributes(lp);
	}

	/**
	 * 屏幕变亮
	 */
	public static void setScreenBright(Context mContext) {
		WindowManager.LayoutParams lp = ((Activity) mContext).getWindow()
				.getAttributes();
		lp.alpha = 1f;
		((Activity) mContext).getWindow().setAttributes(lp);
	}

	/**
	 * 打开软键盘
	 */
	public static void openKeybord(EditText mEditText, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
				InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	/**
	 * 关闭软键盘
	 */
	public static void closeKeybord(EditText mEditText, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
	}

	/**
	 * 将分为单位的转换为元并返回金额格式的字符串 （除100）
	 *
	 * @param amount
	 * @return
	 */

	public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";

	public static String changeF2Y(Long amount) throws Exception {
		if (!amount.toString().matches(CURRENCY_FEN_REGEX)) {
			throw new Exception("金额格式有误");
		}

		int flag = 0;
		String amString = amount.toString();
		if (amString.charAt(0) == '-') {
			flag = 1;
			amString = amString.substring(1);
		}
		StringBuffer result = new StringBuffer();
		if (amString.length() == 1) {
			result.append("0.0").append(amString);
		} else if (amString.length() == 2) {
			result.append("0.").append(amString);
		} else {
			String intString = amString.substring(0, amString.length() - 2);
			for (int i = 1; i <= intString.length(); i++) {
				if ((i - 1) % 3 == 0 && i != 1) {
					result.append(",");
				}
				result.append(intString.substring(intString.length() - i,
						intString.length() - i + 1));
			}
			result.reverse().append(".")
					.append(amString.substring(amString.length() - 2));
		}
		if (flag == 1) {
			return "-" + result.toString().replaceAll(",","");
		} else {
			return result.toString().replaceAll(",","");
		}
	}

	/**
     * 将元为单位的转换为分 替换小数点，支持以逗号区分的金额
     *
     * @param amount
     * @return
     */
    public static String changeY2F(String amount){
        String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额
        int index = currency.indexOf(".");
        int length = currency.length();
        Long amLong = 0l;
        if(index == -1){
            amLong = Long.valueOf(currency+"00");
        }else if(length - index >= 3){
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));
        }else if(length - index == 2){
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);
        }else{
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");
        }
        return amLong.toString();
    }
	//弹出软键盘
	public static void ShowSystemKeyboard(Context c){
		InputMethodManager imm = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}
	//隐藏软键盘
	public static void HideSystemKeyboard(Context c,EditText ed){
		InputMethodManager imm = (InputMethodManager) c
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(ed.getWindowToken(), 0);
	}

	/**
	 * 隐藏软键盘的方法
	 * @param context
	 * @param v
     * @return
     */
	public static Boolean hideInputMethod(Context context, View v) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm != null) {
			return imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
		}
		return false;
	}

	/**
	 * 判断当前点击屏幕的地方是否是软键盘：
	 * @param v
	 * @param event
     * @return
     */
	public static boolean isShouldHideInput(View v, MotionEvent event) {
		if (v != null && (v instanceof EditText)) {
			int[] leftTop = { 0, 0 };
			v.getLocationInWindow(leftTop);
			int left = leftTop[0], top = leftTop[1], bottom = top + v.getHeight(), right = left
					+ v.getWidth();
			if (event.getX() > left && event.getX() < right
					&& event.getY() > top && event.getY() < bottom) {
				// 保留点击EditText的事件
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * 跳转到拨号界面，同时传递电话号码
	 * @param c
	 * @param num
	 */
	public static void IntentCallAction(Context c,String num){
		try {
			//跳转到拨号界面，同时传递电话号码
			Intent dialIntent =  new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + num));
			c.startActivity(dialIntent);
		}catch (Exception e){
		}

	}


	/**
	 * 产生范围随机数
	 * @param min
	 * @param max
	 * @return
	 */
	public static int GetRandomNum(int min,int max){

		Random random = new Random();
		int num = random.nextInt(max)%(max-min+1) + min;
		return num;
	}

	/**
	 * 产生指定位数的随机数
	 * @param digit
	 * @return
	 */
	public static int GetRandomNum(int digit){
		int num = (int) ((Math.random() * 9 + 1) * Math.pow(10,digit));
		return num;
	}

	/**
	 * 产生指定位数的随机数 前面补0
	 * @param digit
	 * @return
	 */
	public static String GetRandomNumForString(int digit){
		double  num = (int) ((Math.random() * 9 + 1) * Math.pow(10,digit));
		String fixLenthString = String.valueOf(num);
		// 返回固定的长度的随机数
		return fixLenthString.substring(1, digit + 1);

	}
}
