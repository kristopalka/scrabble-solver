import {StyleSheet, Text, TextInput, View} from 'react-native';
import {getLetterValue, isValidLetter} from '../../javascript/scrabble';
import {useState} from "react";

function preprocessLetter(letter) {
    return letter === " " || letter === null ?
        "" :
        letter.toUpperCase();
}

export default function Field(props) {
    const [letter, setLetter] = useState(preprocessLetter(props.letter))
    const editMode = props.onUpdateLetter;


    function changeLetter(newLetter) {
        if (isValidLetter(newLetter) || newLetter === "") {
            newLetter = newLetter.toUpperCase();
            setLetter(newLetter);
            props.onUpdateLetter(newLetter);
        }

    }

    const textInput = <TextInput
        style={styles.letter}
        maxLength={1}
        clearTextOnFocus={true}
        onChangeText={(l) => changeLetter(l)}
        value={letter}/>

    return (
        <View style={styles.field}>
            {editMode ? textInput : <Text style={styles.letter}>{letter}</Text>}
            <Text style={styles.value}>{getLetterValue(letter)}</Text>
        </View>
    );
}

const styles = StyleSheet.create({
    field: {
        backgroundColor: "white",
        height: "100%",
        width: "100%",
        justifyContent: "center",
        alignItems: "center",
        borderWidth: 1,
        borderColor: "gray",
        borderRadius: 5,
    },
    letter: {
        textAlign: "center",
        textAlignVertical: "center",
        includeFontPadding: false,
        fontWeight: "bold",
        fontSize: 18,
    },
    value: {
        position: "absolute",
        left: 22,
        top: 19,
        includeFontPadding: false,
        fontWeight: "bold",
        fontSize: 8,
    }

});
