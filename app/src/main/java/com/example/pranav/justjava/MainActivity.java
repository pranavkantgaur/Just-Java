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
    // this method is called when 'ORDER' button is pressed.
    public void submitOrder(View view)
    {

//        orderSummary();
        String senderAddress  = "justjava@example.com";

        String message;
        int pricePerUnit = 5;
        EditText custName = (EditText) findViewById(R.id.customer_name_text);
        String subject = "Just Java Order for " + custName.getText().toString();
        message = "Name: " + custName.getText().toString();
        message += "\nAdd whipped cream?";
        if (isWhippedCreamChecked)
        {
            message += "true";
            pricePerUnit += 1;
        }
        else
            message += "false";
        message += "\nAdd chocolate?";
        if (isChocolateChecked)
        {
            message += "true";
            pricePerUnit += 2;
        }
        else
            message += "false";
        message += "\nQuantity: " + quantity;
        message += "\nTotal: #" + quantity * pricePerUnit;
        message += "\nThank you!";

        // setup intents
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, senderAddress);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
    private void orderSummary()
    {

        int pricePerUnit = 5;
        EditText custName = (EditText) findViewById(R.id.customer_name_text);
        String message = "Name: " + custName.getText().toString();
        message += "\nAdd whipped cream?";
        if (isWhippedCreamChecked)
        {
            message += "true";
            pricePerUnit += 1;
        }
        else
            message += "false";
        message += "\nAdd chocolate?";
        if (isChocolateChecked)
        {
            message += "true";
            pricePerUnit += 2;
        }
        else
            message += "false";
        message += "\nQuantity: " + quantity;
        message += "\nTotal: #" + quantity * pricePerUnit;
        message += "\nThank you!";
        TextView view = (TextView) findViewById(R.id.order_summary_textview);
        view.setText(message);

    }


    public void increaseQuantity(View view)
    {
        quantity++;
        displayQuantity(quantity);
    }

    public void decreaseQuantity(View view) {
        if (quantity > 1)
            quantity--;
        else
        {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(quantity);
    }

    private void displayQuantity(int number)
    {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void setIsWhippedCreamChecked(View view) {
        CheckBox whippedCreamCB = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        if (whippedCreamCB.isChecked())
            isWhippedCreamChecked = true;
        else
            isWhippedCreamChecked = false;
    }

    public void setIsChocolateChecked(View view)
    {
        CheckBox chocolateCB = (CheckBox) findViewById(R.id.chocolate_checkbox);
        if (chocolateCB.isChecked())
            isChocolateChecked = true;
        else
            isChocolateChecked = false;
    }
}
