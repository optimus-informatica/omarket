/*
 * Copyright 2019 darkx.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.osasco.optimus.omarket;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.text.BadLocationException;

/**
 *
 * @author darkx
 */
public final class ACComboBox extends JComboBox<String> {
    public int caretPos = 0;
    public JTextField textField;
    
    public ACComboBox(final String items[]) {
        super(items);
        setEditor(new BasicComboBoxEditor());
        setEditable(true);
    }
    
    @Override
    public void setSelectedIndex(int index) {
        super.setSelectedIndex(index);
        textField.setText(getItemAt(index));
        textField.setSelectionEnd(caretPos + textField.getText().length());
        textField.moveCaretPosition(caretPos);
    }
    
    @Override
    public void setEditor(ComboBoxEditor editor) {
        super.setEditor(editor);
        if (editor.getEditorComponent() instanceof JTextField) {
            textField = (JTextField) editor.getEditorComponent();
            textField.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    char key = e.getKeyChar();
                    if (!(Character.isLetterOrDigit(key) || Character.isSpaceChar(key))) {
                        return ;
                    }
                    caretPos = textField.getCaretPosition();
                    String text = "";
                    try {
                        text = textField.getText(0, caretPos);
                    }
                    catch (BadLocationException ex) {
                        ex.printStackTrace();
                    }
                    
                    for (int i=0; i<getItemCount(); i++) {
                        String item = getItemAt(i);
                        if (item.startsWith(text)) {
                            setSelectedIndex(i);
                            return;
                        }
                    }
                }
            });
        }
    }
}
