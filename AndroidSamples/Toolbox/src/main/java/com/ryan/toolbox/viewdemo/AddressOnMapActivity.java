package com.ryan.toolbox.viewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ryan.toolbox.R;
import com.ryan.toolbox.tool.Common;

/**
 *
 */
public class AddressOnMapActivity extends Activity{

    EditText et_Url;
    Button btnOpenUrl;
    private AddressOnMapActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_on_map);
        mContext = AddressOnMapActivity.this;
        init();
    }

    private void init() {

        et_Url = (EditText) findViewById(R.id.et_address);
        btnOpenUrl = (Button) findViewById(R.id.btnOpenMap);

        btnOpenUrl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Common.isNetworkAvailable(mContext)) {
                    if (Common.isEmptyEditText(et_Url)) {
                        Common.showAddressOnMap(mContext, "Ahmedabad, Gujarat");
                    } else {
                        Common.showAddressOnMap(mContext, et_Url.getText().toString());
                    }
                } else {
                    Common.showNETWORDDisabledAlert(mContext);
                }

            }
        });
    }

}
