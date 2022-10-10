import {StyleSheet, Text, TextInput, TouchableOpacity, View} from 'react-native';
import {empty, mark} from '../../javascript/scrabble';
import {useEffect, useState} from "react";


export default function Field(props) {
    const lettersValues = props.lettersValues;

    const [editing, setEditing] = useState(false);
    const [letter, setLetter] = useState(getLetter(props.input));
    const [marked, setMarked] = useState(isMarked(props.input));
    let textInput;

    useEffect(() => {
        setLetter(getLetter(props.input));
        setMarked(isMarked(props.input));
    }, [props.input]);

    function getLetter(input) {
        if (input === empty) return empty;
        if (input === "") return empty;

        const letter = input[0].toUpperCase();
        if (lettersValues.isLetterOrEmptySymbol(letter)) {
            return letter;
        } else {
            if(props.updateLetter) props.updateLetter(empty);
            return empty;
        }
    }

    function isMarked(input) {
        return input[1] === mark;
    }

    function textChange(text) {
        const newSymbol = text.length > 0 ? text[text.length - 1].toUpperCase() : empty;
        if (lettersValues.isLetterOrEmptySymbol(newSymbol)) {
            props.updateLetter(newSymbol);
            setLetter(newSymbol);
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

    const inputComponent = <TextInput
        defaultValue={letter} ref={input => (textInput = input)} autoFocus={true} onBlur={editingEnd}
        onChangeText={(text) => textChange(text)} style={{display: 'none'}}/>

    return (
        <TouchableOpacity style={styles.touchable(props.size)} onPress={editingStart}>
            <View style={styles.field(editing, props.size, marked)}>
                <Text style={styles.letter(props.size)}>{letter}</Text>
                <Text style={styles.value(editing, props.size)}>{lettersValues.getLetterValue(letter)}</Text>
                {editing ? inputComponent : ''}
            </View>
        </TouchableOpacity>
    );
}

const styles = StyleSheet.create({
    touchable: (size) => ({
        height: size,
        width: size,
    }),
    field: (editing, size, marked) => ({
        justifyContent: "center",
        alignItems: "center",
        height: "100%",
        width: "100%",
        backgroundColor: editing ? "#d9d9d9" : (marked ? "lightgreen" : "white"),
        borderWidth: editing || marked ? (size * 2 / 32) : (size / 32),
        borderColor: editing ? "#777777" : (marked ? "green" : "gray"),
        borderRadius: size * 0.15,
    }),
    letter: (size) => ({
        textAlign: "center",
        textAlignVertical: "center",
        includeFontPadding: false,
        fontWeight: "bold",
        left: -1,
        fontSize: size * 0.56,
    }),
    value: (editing, size, marked) => ({
        position: "absolute",
        left: editing || marked ? (size * 20 / 32) : (size * 21 / 32),
        top: editing || marked ? (size * 18 / 32) : (size * 19 / 32),

        fontWeight: "bold",
        fontSize: (size * 0.24),
    }),
});
