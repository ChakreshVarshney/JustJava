package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/*public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}*/







/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    int whippedCreamToppingPrice = 1;
    int chocolateToppingPrice = 2;
    int plainCoffeePrice = 5;
    int coffeePrice = plainCoffeePrice;
    String to = "chakreshvarshney@gmail.com";
    String subject = "Practice Android Intent";
    int orderNo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        /*Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:47.6,-122.3"));
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }*/
        orderNo++;
        String message;
        //int quantity = 2;
        if(quantity<1||quantity>100)
            return;
        StringBuffer sb = new StringBuffer();

        //display(quantity);
        //String str  = "Free";
        //displayPrice(coffeePrice*quantity);
        EditText v2 = (EditText) findViewById(R.id.name_view);
        String name = v2.getText().toString();
        int  cp=coffeePrice;
        CheckBox v = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        if(v.isChecked())
            cp += whippedCreamToppingPrice;
        CheckBox v1 = (CheckBox) findViewById(R.id.chocolate_checkbox);
        if(v1.isChecked())
            cp += chocolateToppingPrice;

        String Name = getString(R.string.name);
        String Quantity = getString(R.string.quantity);
        String Add = getString(R.string.add);
        String Whipped = getString(R.string.whipped);
        String Cream = getString(R.string.cream);
        sb.append(Name+": "+name+"\n"+Quantity+": "+quantity+"\n"+Add+" "+Whipped+" "+Cream+"? "+v.isChecked()+"\nadd Chocolate? "+v1.isChecked()+"\nTotal: "+cp*quantity+"\nThankyou!!!");

        displayMessage(sb.toString());



        message = sb.toString();
        Intent email = new Intent(Intent.ACTION_SEND);
        email.setData(Uri.parse("mailto:"));//only email app should handel it.
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
        email.putExtra(Intent.EXTRA_SUBJECT, "order no. = "+orderNo);
        email.putExtra(Intent.EXTRA_TEXT, message);

//need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }
    public void increment(View view){
        //int quantity = 2;
        if(quantity==100){
            display("quantity must be less than 100");
            return;
        }
        quantity = quantity+1;
        display(quantity);
    }

    /*public void addTopping(View view){
        coffeePrice = plainCoffeePrice+toppingPrice;
    }*/

    public void decrement(View view){
        //int quantity = 1;
        if(quantity<=0){
            display("quantity must be +ve");
            return;
        }
        quantity--;
        display(quantity);
    }


    /**
     * This method displays the given quantity value on the screen.
     */

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void display(String str) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(str);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}