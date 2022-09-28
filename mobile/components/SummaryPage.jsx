import {StyleSheet, View} from 'react-native';
import Board from "./scrabble/Board";
import Holder from "./scrabble/Holder";
import WordList from "./other/WordList";
import {useState} from "react";
import {addWordToBoard, cloneBoard} from "../javascript/scrabble";
import Break from "./other/Break";


export default function SummaryPage(props) {
    const [displayedBoard, changeDisplayedBoard] = useState(props.board);

    function choseWord(word) {
        let newBoard = addWordToBoard(cloneBoard(props.board), word);
        changeDisplayedBoard(newBoard);
    }

    return (
        <View style={styles.container}>
            <Board content={displayedBoard} editMode={false} lettersValues={props.lettersValues}/>

            <View style={styles.panel}>
                <Holder content={props.holder} editMode={false} lettersValues={props.lettersValues}/>
                <Break/>
                <WordList words={props.words} style={styles.wordlist} choseWord={choseWord}/>
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        backgroundColor: "black",
        height: "100%",
        width: "100%",
    },
    panel: {
        backgroundColor: "white",
        padding: 20,
        borderTopWidth: 1,
        borderColor: "black",
    },
    wordlist: {
        height: 200,
    }

});


