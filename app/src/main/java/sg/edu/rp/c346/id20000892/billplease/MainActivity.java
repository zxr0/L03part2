package sg.edu.rp.c346.id20000892.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {

    EditText amount;
    EditText pax;
    ToggleButton svs;
    ToggleButton gst;
    EditText discount;
    RadioGroup pay;
    Button split;
    Button reset;
    TextView total;
    TextView totalsplit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.AmountN);
        pax = findViewById(R.id.PaxN);
        svs = findViewById(R.id.SVS);
        gst = findViewById(R.id.GST);
        discount = findViewById(R.id.DiscountN);
        pay = findViewById(R.id.Radio);
        split = findViewById(R.id.S);
        reset = findViewById(R.id.Reset);
        total = findViewById(R.id.TotalBill);
        totalsplit = findViewById(R.id.SplitBill);


        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double gstnum = 1;
                if (gst.isChecked()){
                    gstnum = 1.07;
                }

                double svsnum = 1;
                if (svs.isChecked()){
                    svsnum = 1.1;
                }

                String a = amount.getText().toString();
                double amountnum = Double.parseDouble(a);

                String b = pax.getText().toString();
                double paxnum = Double.parseDouble(b);

                String c = discount.getText().toString();
                double discount1 = Double.parseDouble(c);
                double discnum = (100-discount1)/100;

                double totalamount = (amountnum) * svsnum * gstnum * discnum;
                double totaldivided = totalamount/paxnum;
                String output = String.format("Total Bill: $%.2f",totalamount);

                if (pay.getCheckedRadioButtonId() == R.id.CashR){

                    String output2 = String.format("Each pays: $%.2f in cash", totaldivided);
                    total.setText(output);
                    totalsplit.setText(output2);
                }
                if (pay.getCheckedRadioButtonId() == R.id.PayNowR){
                    String output2 = String.format("Each pays: $%.2f via PayNow to 912345678", totaldivided);
                    total.setText(output);
                    totalsplit.setText(output2);
                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                pax.setText("");
                gst.setChecked(false);
                svs.setChecked(false);
                discount.setText("");
                pay.clearCheck();
            }
        });

    }
}