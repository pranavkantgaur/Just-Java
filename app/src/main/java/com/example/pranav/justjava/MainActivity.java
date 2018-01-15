package com.example.pranav.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    static int quantity = 0;
    static boolean isWhippedCreamChecked = false;
    static boolean isChocolateChecked = false;
    int pricePerUnit = 5;
    // this method is called when 'ORDER' button is pressed.
    public void submitOrder(View view)
    {

//        orderSummary();
        String senderAddress  = getString(R.string.sender_address);
        String message;
        EditText custName = findViewById(R.id.customer_name_text);
        String subject = getString(R.string.subject_initial) + " " + custName.getText().toString();
        message = getString(R.string.name_template) + custName.getText().toString();
        message += getString(R.string.check_wipped_cream_text);
        if (isWhippedCreamChecked)
        {
            message += getString(R.string.true_response);
            //pricePerUnit += 1;
        }
        else
            message += getString(R.string.false_response);
        message += getString(R.string.check_chocolate_text);
        if (isChocolateChecked)
        {
            message += getString(R.string.true_response);
            //pricePerUnit += 2;
        }
        else
            message += getString(R.string.false_response);
        message += getString(R.string.quantity_template) + quantity;
        message += getString(R.string.total_cost_template)+ NumberFormat.getCurrencyInstance().format(quantity * pricePerUnit);
        message += getString(R.string.thanks_template);

        // setup intents
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse(getString(R.string.mail_to)));
        intent.putExtra(Intent.EXTRA_EMAIL, senderAddress);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    private void displayCurrentPrice()
    {
//        if (isWhippedCreamChecked)
//        {
//            pricePerUnit += 1;
//        }
//        if (isChocolateChecked)
//        {
//            pricePerUnit += 2;
//        }
        int currentPrice= quantity * pricePerUnit;
        TextView currentPriceTextView = findViewById(R.id.order_total_textview);
        currentPriceTextView.setText(NumberFormat.getCurrencyInstance().format(currentPrice));
    }
    public void increaseQuantity(View view)
    {
        quantity++;
        displayQuantity();
        displayCurrentPrice();
    }

    public void decreaseQuantity(View view) {
        if (quantity > 1)
            quantity--;
        else
        {
            Toast.makeText(this, getString(R.string.order_underflow_error), Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity();
        displayCurrentPrice();
    }

    private void displayQuantity()
    {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(Integer.toString(quantity));
    }

    public void setIsWhippedCreamChecked(View view) {
        CheckBox whippedCreamCB = findViewById(R.id.whipped_cream_checkbox);
        if (whippedCreamCB.isChecked())
        {
            isWhippedCreamChecked = true;
            pricePerUnit += 1;
        }
        else
        {
            isWhippedCreamChecked = false;
            pricePerUnit -= 1;
        }
        displayCurrentPrice();
    }

    public void setIsChocolateChecked(View view)
    {
        CheckBox chocolateCB = findViewById(R.id.chocolate_checkbox);
        if (chocolateCB.isChecked())
        {
            isChocolateChecked = true;
            pricePerUnit += 2;
        }
        else
        {
            isChocolateChecked = false;
            pricePerUnit -= 2;
        }
        displayCurrentPrice();
    }
}
