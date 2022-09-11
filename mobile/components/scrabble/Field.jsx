import {StyleSheet, Text, TextInput, TouchableOpacity, View} from 'react-native';
import {empty, getLetterValue, isValidLetter} from '../../javascript/scrabble';
import {useState} from "react";

function preprocessLetter(text) {
    if(text === empty || text === "" || text === null) {
        return empty;
    }
    const letter = text[text.length - 1];
    if(isValidLetter(letter)) {
        return letter.toUpperCase();
    }
    return empty;
}

export default function Field(props) {
    const [letter, setLetter] = useState(preprocessLetter(props.letter))
    const [editing, setEditing] = useState(false)
    let textInput;


    function textChange(text) {
        const newLetter = text.length > 0 ? text[text.length - 1] : empty;
        if (isValidLetter(newLetter) || newLetter === empty) {
            const upperNewLetter = newLetter.toUpperCase();
            setLetter(upperNewLetter);
            props.onUpdateLetter(upperNewLetter);
        }
    }

    function editingStart() {
        if (props.editMode) {
            setEditing(true);
        }
    }

    function editingEnd() {
        setEditing(false);
    }



    return (
        <TouchableOpacity style={styles.touchable(props.size)} onPress={editingStart}>
            <View style={styles.field(editing, props.size)}>
                <Text style={styles.letter(props.size)}>{letter}</Text>
                <Text style={styles.value(editing, props.size)}>{getLetterValue(letter)}</Text>

                {editing ? <TextInput
                        defaultValue={letter}
                        ref={input => (textInput = input)}
                        autoFocus={true}
                        onBlur={editingEnd}
                        onChangeText={(text) => textChange(text)}
                        style={{display: 'none'}}/>
                    : ''}
            </View>
        </TouchableOpacity>
    );
}

const styles = StyleSheet.create({
    touchable: (size) => ({
        height: size,
        width: size,
    }),
    field: (editing, size) => ({
        justifyContent: "center",
        alignItems: "center",
        height: "100%",
        width: "100%",
        backgroundColor: editing ? "#d9d9d9" : "white",
        borderWidth: editing ? 2.2 * (size / 32) : 1.2 * (size / 32),
        borderColor: editing ? "#777777" : "gray",
        borderRadius: 5 * (size / 32),
    }),
    letter: (size) => ({
        textAlign: "center",
        textAlignVertical: "center",
        includeFontPadding: false,
        fontWeight: "bold",
        fontSize: 18 * (size / 32),
    }),
    value: (editing, size) => ({
        position: "absolute",
        left: editing ? 21 * (size / 32) : 22 * (size / 32),
        top: editing ? 18 * (size / 32) : 19 * (size / 32),
        includeFontPadding: false,
        fontWeight: "bold",
        fontSize: 8 * (size / 32),
    }),
});
