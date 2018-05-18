package com.mingxuan.huaji.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mingxuan.huaji.base.Constants;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	
	private static String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
	
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.pay_result);
        
    	api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);

    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		Log.e(TAG, "onPayFinish, errStr = " + resp.errStr);

//		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//			AlertDialog.Builder builder = new AlertDialog.Builder(this);
//			builder.setTitle("支付结果");
//			builder.setMessage("支付成功");
//			builder.show();
//		}

		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			int code = resp.errCode;
			switch (code) {
				case 0:
					Toast.makeText(WXPayEntryActivity.this,"支付成功", Toast.LENGTH_SHORT).show();
					break;
				case -1:
					Toast.makeText(WXPayEntryActivity.this,"支付失败-1", Toast.LENGTH_SHORT).show();
					finish();
					break;
				case -2:
					Toast.makeText(WXPayEntryActivity.this,"取消支付", Toast.LENGTH_SHORT).show();
					finish();
					break;
				default:
					Toast.makeText(WXPayEntryActivity.this,"其他失败", Toast.LENGTH_SHORT).show();
					setResult(RESULT_OK);
					finish();
					break;
			}
		}
	}
}