package com.example.notepad;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

public class OneNoteActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one_note);

		EditText textEdit = findViewById(R.id.textEdit);
		textEdit.setText(new SpannableString(""));

		Button colorButton = findViewById(R.id.textColorButton);
		Button backColorButton = findViewById(R.id.backColorButton);
		Button styleButton = findViewById(R.id.styleButton);

		// Зміна кольору тексту
		colorButton.setOnLongClickListener(v -> {
			PopupMenu popupMenu = new PopupMenu(OneNoteActivity.this, v);
			popupMenu.getMenuInflater().inflate(R.menu.color_button_menu, popupMenu.getMenu());

			popupMenu.setOnMenuItemClickListener(item -> {
				int color = getColorFromMenuItem(item);
				applyTextColor(textEdit, color);
				return true;
			});

			popupMenu.show();
			return true;
		});

		backColorButton.setOnLongClickListener(v -> {
			PopupMenu popupMenu = new PopupMenu(OneNoteActivity.this, v);
			popupMenu.getMenuInflater().inflate(R.menu.color_button_menu, popupMenu.getMenu());

			popupMenu.setOnMenuItemClickListener(item -> {
				int color = getColorFromMenuItem(item);
				applyBackgroundColor(textEdit, color);
				return true;
			});

			popupMenu.show();
			return true;
		});


		styleButton.setOnLongClickListener(v -> {
			PopupMenu popupMenu = new PopupMenu(OneNoteActivity.this, v);
			popupMenu.getMenuInflater().inflate(R.menu.style_button_menu, popupMenu.getMenu());

			popupMenu.setOnMenuItemClickListener(item -> {
				int style = getStyleFromMenuItem(item);
				applyTextStyle(textEdit, style);
				return true;
			});

			popupMenu.show();
			return true;
		});
	}

	private void applyTextColor(EditText textEdit, int color) {
		Spannable spannable = textEdit.getText();
		ForegroundColorSpan span = new ForegroundColorSpan(color);
		spannable.setSpan(span, textEdit.getSelectionStart(), textEdit.getSelectionEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	}

	private void applyBackgroundColor(EditText textEdit, int color) {
		Spannable spannable = textEdit.getText();
		BackgroundColorSpan span = new BackgroundColorSpan(color);
		spannable.setSpan(span, textEdit.getSelectionStart(), textEdit.getSelectionEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	}

	private void applyTextStyle(EditText textEdit, int style) {
		Spannable spannable = textEdit.getText();
		StyleSpan span = new StyleSpan(style);
		spannable.setSpan(span, textEdit.getSelectionStart(), textEdit.getSelectionEnd(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	}

	private int getColorFromMenuItem(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.greenColorMenu) {
			return Color.GREEN;
		} else if (id == R.id.blueColorMenu) {
			return Color.BLUE;
		} else {
			return Color.BLACK;
		}
	}


	private int getStyleFromMenuItem(MenuItem item) {
		int itemId = item.getItemId();

		if (itemId == R.id.boldMenu) {
			return android.graphics.Typeface.BOLD;
		} else if (itemId == R.id.italicMenu) {
			return android.graphics.Typeface.ITALIC;
		} else {
			return android.graphics.Typeface.NORMAL;
		}
	}

}
