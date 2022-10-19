import {StyleSheet, View} from 'react-native';
import Board from "./scrabble/Board";
import Rack from "./scrabble/Rack";
import WordList from "./other/WordList";
import {useState} from "react";
import {markUsedLettersOnRack, markWordOnBoard} from "../javascript/scrabble";
import Break from "./other/Break";
import Navigation from "./other/Navigation";


export default function SummaryPage(props) {
    const [displayedBoard, changeDisplayedBoard] = useState(props.board);
    const [displayedRack, changeDisplayedRack] = useState(props.rack);

    function choseWord(word) {
        changeDisplayedBoard(markWordOnBoard(props.board, word));
        changeDisplayedRack(markUsedLettersOnRack(props.rack, word))
    }

    return (
        <View style={styles.container}>
            <Board content={displayedBoard} editMode={false} lettersValues={props.lettersValues}/>

            <View style={styles.panel}>
                <Rack content={displayedRack} editMode={false} lettersValues={props.lettersValues}/><Break/>

                <WordList words={props.words} style={styles.wordlist} choseWord={choseWord}/><Break/>

                <Navigation
                    onLeftClick={props.goEditBoards}
                    rightIco={"camera"}
                    onRightClick={props.goCamera}
                    helpTitle={"Summary"}
                    helpMessage={"Chose word from list. If there is no words, change board and rack configuration."}
                />

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


