package com.example.noteappcleanarquitecture.feature_note.presentation.notes.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TransparentHintTextField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean,
    onValueChange: (String) -> Unit,
    textStyle: androidx.compose.ui.text.TextStyle,
    singleLine: Boolean = false,
    onFocusChange: (FocusState) -> Unit
){
    Box(modifier = modifier){
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            textStyle = textStyle,
            singleLine = singleLine,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusChange(it)
                }

        )
        if(isHintVisible){
            println("isHintVisible")
            Spacer(modifier = modifier)
            Text(text = hint, style = textStyle, color = Color.DarkGray)

        }else{
            println("isHintVisible not")
        }
    }

}

@Preview
@Composable
fun TransparentHintTextFieldPreview() {
    TransparentHintTextField(
        text = "Hello",
        hint = "Hint",
        isHintVisible = true,
        onValueChange = {},
        textStyle = androidx.compose.ui.text.TextStyle(),
        onFocusChange = {}
    )
}