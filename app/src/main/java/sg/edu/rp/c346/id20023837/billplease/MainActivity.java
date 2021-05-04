package sg.edu.rp.c346.id20023837.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amtInput;
    EditText numInput;
    ToggleButton noSVS;
    ToggleButton gst;
    RadioButton cash;
    RadioButton paynow;
    TextView totalBill;
    TextView eachPay;
    Button split;
    Button reset;
    EditText discount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amtInput = findViewById(R.id.editTextNumber);
        numInput = findViewById(R.id.editTextNumber2);
        noSVS = findViewById(R.id.noSVS);
        gst = findViewById(R.id.gst);
        cash = findViewById(R.id.cashBtn);
        paynow = findViewById(R.id.paynowBtn);
        totalBill = findViewById(R.id.totalBill);
        eachPay = findViewById(R.id.eachPay);
        split = findViewById(R.id.submit);
        reset = findViewById(R.id.reset);
        discount = findViewById(R.id.editTextNumber3);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amt1 = amtInput.getText().toString();
                String amt2 = numInput.getText().toString();
                String amt3 = discount.getText().toString();
                double newAmt = 0.0;

                if (amt1.isEmpty() || amt2.isEmpty() || amt3.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please input number",
                            Toast.LENGTH_SHORT).show();
                } else {

                    if (!noSVS.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amt1);
                    } else if (!noSVS.isChecked() && gst.isChecked()) {
                        newAmt = Double.parseDouble(amt1) * 1.07;

                    } else if (noSVS.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amt1) * 1.10;
                    } else {
                        newAmt = Double.parseDouble(amt1) * 1.17;
                    }


                    if (amt3.trim().length() != 0) {
                        newAmt = newAmt * (1 - Double.parseDouble(amt3) / 100);
                        if (cash.isChecked()) {
                            totalBill.setText("Total Bill: $" + String.format("%.2f", newAmt));
                            int numOfPpl = Integer.parseInt(amt2);
                            if (numOfPpl != 1) {
                                eachPay.setText("Each Pays: $" + String.format("%.2f", (newAmt / numOfPpl)) + " in cash");
                            } else {
                                eachPay.setText("Each Pays: $" + newAmt + " in cash");
                            }

                        } else {
                            totalBill.setText("Total Bill: $" + String.format("%.2f", newAmt));
                            int numOfPpl = Integer.parseInt(amt2);
                            if (numOfPpl != 1) {
                                eachPay.setText("Each Pays: $" + String.format("%.2f", (newAmt / numOfPpl)) + " via PayNow");
                            } else {
                                eachPay.setText("Each Pays: $" + newAmt + " via PayNow");
                            }
                        }
                    }
                }
            }

        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amtInput.setText("");
                numInput.setText("");
                noSVS.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
                cash.setChecked(true);
                paynow.setChecked(false);
            }
        });
    }
}
