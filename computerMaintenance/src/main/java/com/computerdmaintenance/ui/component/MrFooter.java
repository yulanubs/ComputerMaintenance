package com.computerdmaintenance.ui.component;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.computerdmaintenance.ComputerMaintenanceApplication;
import com.computerdmaintenance.R;
import com.loudmaintenance.util.Consts;
import com.loudmaintenance.util.MrLog;
import com.loudmaintenance.util.UtilTools;

import java.util.Calendar;

public class MrFooter extends LinearLayout implements View.OnClickListener {

    public static final int TAB_TAG_INDEX = 1;
    public static final int TAB_TAG_CATEGORY = 2;
    public static final int TAB_TAG_CART = 3;
    public static final int TAB_TAG_PERSONAL = 4;
    public static final int TAB_TAG_SEARCH = 5;
    public static final int TAB_TAG_ROUTE = 6;
    public static final int TAB_TAG_OTHER = -1;
    private static final String TAG = "UleFooter";
    private int screenWidth;
    private int tabWidth;
    private int tabHeight;
    private int tabCount = 5;
    private float scale;
    private int currentTabTag;
    private int cartCount;

    private ComputerMaintenanceApplication app;

    private TextView index, category, cart, personal, search, cartNumber, personalpromot, categorypromot, route;
    private FrameLayout shoppingCar, personalLayout, categoryLayout;
    private Context ctx;

    private OnFootTabChooseListener _l;

    public MrFooter(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        initView(context);
    }

    public MrFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        initView(context);
    }

    @SuppressWarnings("deprecation")
    private void initView(Context context) {

        ctx = context;
        app = (ComputerMaintenanceApplication) context.getApplicationContext();
        setGravity(Gravity.CENTER_VERTICAL);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        tabWidth = screenWidth / tabCount;
        scale = ((float) 180) / ((float) tabWidth);
        tabHeight = (int) (115 / scale);

        LayoutParams normalLayoutParams1 = new LayoutParams(tabWidth, UtilTools.dip2Px(ctx, 45));
        MrLog.error(TAG, "tabWidth: " + tabWidth);
        MrLog.error(TAG, "tabHeight: " + tabHeight);
        index = generateText(context);
        Drawable drawable = getResources().getDrawable(R.drawable.ic_nav_home_outline_32dp);
        drawable.setBounds(0, 0, UtilTools.dip2Px(context, 25), UtilTools.dip2Px(context, 25));
        index.setCompoundDrawables(null, drawable, null, null);
        index.setOnClickListener(this);
        index.setText(R.string.foot_index);
        index.setLayoutParams(normalLayoutParams1);
        index.setBackgroundColor(0xddf9f9f9);
        addView(index);

        LayoutParams normalLayoutParams5 = new LayoutParams(tabWidth, UtilTools.dip2Px(ctx, 45));
        search = generateText(context);
        /*search.setBackgroundResource(R.drawable.tabbar_search_n);*/
        Drawable drawablesearch = getResources().getDrawable(R.drawable.ic_tab_search_normal);
        drawablesearch.setBounds(0, 0, UtilTools.dip2Px(context, 25), UtilTools.dip2Px(context, 25));
        search.setCompoundDrawables(null, drawablesearch, null, null);
        search.setOnClickListener(this);
        search.setText(R.string.foot_search);
        search.setBackgroundColor(0xddf9f9f9);
        addView(search, normalLayoutParams5);

        category = generateText(context);
        category.setBackgroundResource(R.drawable.tabbar_classify_n);
        category.setText(R.string.foot_activity);
        categorypromot = new TextView(context);
        categorypromot.setGravity(Gravity.CENTER);
        categorypromot.setTextColor(Color.WHITE);
        categorypromot.setTextSize(12);
        categorypromot.setBackgroundResource(R.drawable.bg_prompt);
        categorypromot.setVisibility(needShowDot() ? View.VISIBLE : View.GONE);
        FrameLayout.LayoutParams categoryLayoutParams =
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT);
        FrameLayout.LayoutParams categorynumberLayoutParams =
                new FrameLayout.LayoutParams((int) (14 / scale), (int) (14 / scale));
        categorynumberLayoutParams.gravity = Gravity.RIGHT | Gravity.TOP;
        categorynumberLayoutParams.setMargins(0, UtilTools.dip2Px(context, 10), UtilTools.dip2Px(context, 10), 0);
        categoryLayout = new FrameLayout(context);
        categoryLayout.addView(category, categoryLayoutParams);
        categoryLayout.addView(categorypromot, categorynumberLayoutParams);
        categoryLayout.setOnClickListener(this);
//		addView(categoryLayout, normalLayoutParams2);

        LayoutParams normalLayoutParams3 = new LayoutParams(tabWidth, UtilTools.dip2Px(ctx, 45));
        cart = generateText(context);
        /*cart.setBackgroundResource(R.drawable.tabbar_shopping_n);*/
        Drawable drawablecart = getResources().getDrawable(R.drawable.ic_search_disease_n);
        drawablecart.setBounds(0, 0, UtilTools.dip2Px(ctx, 25), UtilTools.dip2Px(ctx, 25));
        cart.setCompoundDrawables(null, drawablecart, null, null);
        cart.setPadding(0, 3, 0, (int) (8 / scale));
        cart.setCompoundDrawablePadding(2);
        cart.setText(R.string.foot_cart);
        cartNumber = new TextView(context);
        cartNumber.setGravity(Gravity.CENTER);
        cartNumber.setTextColor(Color.WHITE);
        cartNumber.setTextSize(10);
        cartNumber.setBackgroundResource(R.drawable.bg_prompt_r);
        FrameLayout.LayoutParams cartLayoutParams =
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT);
        FrameLayout.LayoutParams numberLayoutParams =
                new FrameLayout.LayoutParams((int) (50 / scale), (int) (50 / scale));
        numberLayoutParams.gravity = Gravity.RIGHT | Gravity.TOP;
        numberLayoutParams.setMargins(0, 0, UtilTools.dip2Px(context, 15), 0);
        shoppingCar = new FrameLayout(context);
        shoppingCar.addView(cart, cartLayoutParams);
        shoppingCar.addView(cartNumber, numberLayoutParams);
        cartNumber.setVisibility(View.GONE);
        shoppingCar.setOnClickListener(this);
        shoppingCar.setBackgroundColor(0xddf9f9f9);
        addView(shoppingCar, normalLayoutParams3);

        route = generateText(context);
        Drawable drawable6 = getResources().getDrawable(R.drawable.ic_nav_messages_outline_32dp);
        drawable6.setBounds(0, 0, UtilTools.dip2Px(context, 25), UtilTools.dip2Px(context, 25));
        route.setCompoundDrawables(null, drawable6, null, null);
        route.setOnClickListener(this);
        route.setText(R.string.foot_route);
        route.setLayoutParams(normalLayoutParams1);
        route.setBackgroundColor(0xddf9f9f9);
        addView(route);

        LayoutParams normalLayoutParams4 = new LayoutParams(tabWidth, UtilTools.dip2Px(ctx, 45));
        personal = generateText(context);
        /*personal.setBackgroundResource(R.drawable.tabbar_personal_n);*/
        Drawable drawablepersonal = getResources().getDrawable(R.drawable.ic_nav_me_outline_32dp);
        drawablepersonal.setBounds(0, 0, UtilTools.dip2Px(ctx, 25), UtilTools.dip2Px(ctx, 25));
        personal.setCompoundDrawables(null, drawablepersonal, null, null);
        personal.setText(R.string.foot_personal);
        personalpromot = new TextView(context);
        personalpromot.setBackgroundResource(R.drawable.icon_prompt);
        FrameLayout.LayoutParams personalLayoutParams =
                new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT);
        FrameLayout.LayoutParams personalPromotParams =
                new FrameLayout.LayoutParams((int) (16 / scale), (int) (16 / scale));
        personalPromotParams.gravity = Gravity.RIGHT | Gravity.TOP;
        personalPromotParams.setMargins(0, UtilTools.dip2Px(context, 5), UtilTools.dip2Px(context, 5), 0);
        personalLayout = new FrameLayout(context);
        personalLayout.addView(personal, personalLayoutParams);
        personalLayout.addView(personalpromot, personalPromotParams);
        personalpromot.setVisibility(View.GONE);
        personalLayout.setOnClickListener(this);
        personalLayout.setBackgroundColor(0xddf9f9f9);
        addView(personalLayout, normalLayoutParams4);

        this.setPadding(0, 1, 0, 0);
        setBackgroundColor(getResources().getColor(R.color.bottom_color_secondary));


    }

    @SuppressWarnings("deprecation")
    private TextView generateText(Context context) {
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(0, 6, 0, (int) (8 / scale));
        textView.setTextSize(10);
        textView.setTextColor(getResources().getColor(R.color.text_color_secondary));
        return textView;
    }

    @SuppressWarnings("deprecation")
    private void clearTabEffect() {
        Drawable drawable = getResources().getDrawable(R.drawable.ic_nav_home_outline_32dp);
        drawable.setBounds(0, 0, UtilTools.dip2Px(ctx, 25), UtilTools.dip2Px(ctx, 25));
        index.setCompoundDrawables(null, drawable, null, null);


        index.setTextColor(getResources().getColor(R.color.text_color_secondary));
        category.setBackgroundResource(R.drawable.tabbar_classify_n);
        category.setTextColor(getResources().getColor(R.color.text_color_secondary));
		/*cart.setBackgroundResource(R.drawable.tabbar_shopping_n);*/
        Drawable drawablecart = getResources().getDrawable(R.drawable.ic_search_disease_n);
        drawablecart.setBounds(0, 0, UtilTools.dip2Px(ctx, 25), UtilTools.dip2Px(ctx, 25));
        cart.setCompoundDrawables(null, drawablecart, null, null);
        cart.setTextColor(getResources().getColor(R.color.text_color_secondary));
		
		/*personal.setBackgroundResource(R.drawable.tabbar_personal_n);*/
        Drawable drawablepersonal = getResources().getDrawable(R.drawable.ic_nav_me_outline_32dp);
        drawablepersonal.setBounds(0, 0, UtilTools.dip2Px(ctx, 25), UtilTools.dip2Px(ctx, 25));
        personal.setCompoundDrawables(null, drawablepersonal, null, null);
        personal.setTextColor(getResources().getColor(R.color.text_color_secondary));
		/*search.setBackgroundResource(R.drawable.tabbar_search_n);*/
        Drawable drawablesearch = getResources().getDrawable(R.drawable.ic_tab_search_normal);
        drawablesearch.setBounds(0, 0, UtilTools.dip2Px(ctx, 25), UtilTools.dip2Px(ctx, 25));
        search.setCompoundDrawables(null, drawablesearch, null, null);
        search.setTextColor(getResources().getColor(R.color.text_color_secondary));

        Drawable drawableRoute = getResources().getDrawable(R.drawable.ic_nav_messages_outline_32dp);
        drawableRoute.setBounds(0, 0, UtilTools.dip2Px(ctx, 25), UtilTools.dip2Px(ctx, 25));
        route.setCompoundDrawables(null, drawableRoute, null, null);
        route.setTextColor(getResources().getColor(R.color.text_color_secondary));
    }

    public void setOnFootTabChooseListener(OnFootTabChooseListener l) {
        _l = l;
    }

    @SuppressWarnings("deprecation")
    public void setChoseTab(int tag) {
        switch (tag) {
            case TAB_TAG_INDEX:
                clearTabEffect();
                currentTabTag = TAB_TAG_INDEX;

                Drawable drawable = getResources().getDrawable(R.drawable.ic_nav_home_filled_32dp);
                drawable.setBounds(0, 0, UtilTools.dip2Px(ctx, 25), UtilTools.dip2Px(ctx, 25));
                index.setCompoundDrawables(null, drawable, null, null);
                index.setTextColor(getResources().getColor(R.color.text_color_bottom));
                break;
            case TAB_TAG_CATEGORY:
                clearTabEffect();
                currentTabTag = TAB_TAG_CATEGORY;
                category.setBackgroundResource(R.drawable.tabbar_classify);
                category.setTextColor(getResources().getColor(R.color.text_color_bottom));
                break;
            case TAB_TAG_CART:
                clearTabEffect();
                currentTabTag = TAB_TAG_CART;
                Drawable drawableCart = getResources().getDrawable(R.drawable.ic_search_disease);
                drawableCart.setBounds(0, 0, UtilTools.dip2Px(ctx, 25), UtilTools.dip2Px(ctx, 25));
                cart.setCompoundDrawables(null, drawableCart, null, null);
                cart.setTextColor(getResources().getColor(R.color.text_color_bottom));
                break;
            case TAB_TAG_PERSONAL:
                clearTabEffect();
                currentTabTag = TAB_TAG_PERSONAL;
/*			personal.setBackgroundResource(R.drawable.tabbar_personal);*/
                Drawable drawablepersonal = getResources().getDrawable(R.drawable.ic_nav_me_filled_32dp);
                drawablepersonal.setBounds(0, 0, UtilTools.dip2Px(ctx, 25), UtilTools.dip2Px(ctx, 25));
                personal.setCompoundDrawables(null, drawablepersonal, null, null);
                personal.setTextColor(getResources().getColor(R.color.text_color_bottom));
                break;
            case TAB_TAG_SEARCH:
                clearTabEffect();
                currentTabTag = TAB_TAG_SEARCH;
			/*search.setBackgroundResource(R.drawable.tabbar_search);*/
                Drawable drawablesearch = getResources().getDrawable(R.drawable.ic_tab_search_pressed);
                drawablesearch.setBounds(0, 0, UtilTools.dip2Px(ctx, 25), UtilTools.dip2Px(ctx, 25));
                search.setCompoundDrawables(null, drawablesearch, null, null);
                search.setTextColor(getResources().getColor(R.color.text_color_bottom));
                break;
            case TAB_TAG_OTHER:
                currentTabTag = TAB_TAG_OTHER;
                break;
            case TAB_TAG_ROUTE:
                clearTabEffect();
                currentTabTag = TAB_TAG_ROUTE;
                Drawable drawableRoute = getResources().getDrawable(R.drawable.ic_nav_messages_filled_32dp);
                drawableRoute.setBounds(0, 0, UtilTools.dip2Px(ctx, 25), UtilTools.dip2Px(ctx, 25));
                route.setCompoundDrawables(null, drawableRoute, null, null);
                route.setTextColor(getResources().getColor(R.color.text_color_bottom));
                break;
        }
    }

    public void setCartCount(int count) {
        if (count > 0) {
            cartCount = count;
            cartNumber.setText(String.valueOf(cartCount));
            cartNumber.setVisibility(View.VISIBLE);
        } else {
            cartCount = 0;
            cartNumber.setText(String.valueOf(0));
            cartNumber.setVisibility(View.GONE);
        }
    }

    public void setPersonPromot(int visibility) {
        personalpromot.setVisibility(visibility);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == index) {
            if (currentTabTag == TAB_TAG_INDEX) {
                return;
            }
            setChoseTab(TAB_TAG_INDEX);
            if (_l != null) {
                _l.onIndexTab();
            }

        } else if (v == categoryLayout) {
            if (categorypromot.getVisibility() == View.VISIBLE) {
                categorypromot.setVisibility(View.GONE);
                app.setSharedPreferences(Consts.Preference.CATEGORY_CLICK_TIME, String.valueOf(System.currentTimeMillis()));
            }
            if (currentTabTag == TAB_TAG_CATEGORY) {
                return;
            }
            setChoseTab(TAB_TAG_CATEGORY);
            if (_l != null) {
                _l.onCategoryTab();
            }

        } else if (v == shoppingCar) {
            if (currentTabTag == TAB_TAG_CART) {
                return;
            }
            setChoseTab(TAB_TAG_CART);
            if (_l != null) {
                _l.onCartTab();
            }

        } else if (v == personalLayout) {
            if (currentTabTag == TAB_TAG_PERSONAL) {
                return;
            }
            setChoseTab(TAB_TAG_PERSONAL);
            if (_l != null) {
                _l.onPersonalTab();
            }

        } else if (v == search) {
            if (currentTabTag == TAB_TAG_SEARCH) {
                return;
            }
            setChoseTab(TAB_TAG_SEARCH);
            if (_l != null) {
                _l.onMoreTab();
            }

        } else if (v == route) {
            setChoseTab(TAB_TAG_ROUTE);
            if (_l != null) {
                _l.onRouteTab();
            }
        }
    }

    /**
     * 是否显示活动上的点
     *
     * @return true是:false否
     */
    private boolean needShowDot() {
        try {
            long cacheTime = Long.valueOf(app.getSharedPreferences(Consts.Preference.CATEGORY_CLICK_TIME));
            long now = System.currentTimeMillis();
            if ((now < cacheTime) || (Math.abs(now - cacheTime) > 1000 * 60 * 60 * 24)) {
                MrLog.debug(TAG, "time is over 24");
                return true;
            } else {
                Calendar cal = Calendar.getInstance();// 使用日历类
                int today = cal.get(Calendar.DAY_OF_MONTH);// 得到天
                cal.setTimeInMillis(cacheTime);
                int temday = cal.get(Calendar.DAY_OF_MONTH);
                MrLog.debug(TAG, "today" + today);
                MrLog.debug(TAG, "temday" + temday);
                return today != temday;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return true;
    }

    public int getFootHeight() {
        return tabHeight;
    }

    public void showCarItems(Integer i) {
//		if (i == null || i <= 0) {
//			mCartNumberTV.setVisibility(View.GONE);
//			return;
//		}
//		mCartNumberTV.setVisibility(View.VISIBLE);
//		String countStr = String.valueOf(i).toString();
//		if (countStr.length() == 2) {
//		}
//		if (countStr.length() > 2) {
//			countStr = countStr.charAt(0) + "..";
//		}
//		mCartNumberTV.setText(countStr);
    }

	/*@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		MrLog.error(TAG, "screenWidth: "+screenWidth);
		MrLog.error(TAG, "tabHeight: "+tabHeight);
		setMeasuredDimension(screenWidth, tabHeight);
	}*/


    public interface OnFootTabChooseListener {
        void onIndexTab();

        void onCategoryTab();

        void onCartTab();

        void onPersonalTab();

        void onMoreTab();

        void onRouteTab();
    }

}
