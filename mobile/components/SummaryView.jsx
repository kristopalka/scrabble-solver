import {StyleSheet, View} from 'react-native';
import ReactNativeZoomableView from '@openspacelabs/react-native-zoomable-view/src/ReactNativeZoomableView';
import Board from "./scrabble/Board";
import Holder from "./scrabble/Holder";
import WordList from "./other/WordList";
import {useState} from "react";
import {addWordToBoard, cloneBoard} from "../javascript/scrabble";


export default function SummaryView(props) {
    const [displayedBoard, changeDisplayedBoard] = useState(props.board);

    function choseWord(word) {
        let newBoard = addWordToBoard(cloneBoard(props.board), word);
        changeDisplayedBoard(newBoard);
    }

    return (
        <View style={styles.container}>
            <ReactNativeZoomableView contentWidth={600} contentHeight={600} maxZoom={4} minZoom={0.7} initialZoom={1}>
                <Board content={displayedBoard} editMode={false}/>
            </ReactNativeZoomableView>

            <View style={styles.panel}>
                <Holder content={props.holder} editMode={false}/>
                <WordList words={props.words} style={styles.wordlist} choseWord={choseWord}/>
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        height: "100%",
        width: "100%",
    },
    panel: {
        backgroundColor: "white",
        padding: 20,
        borderTopWidth: 1,
        borderColor: "black",
    },
    words: {
        flexDirection: "row",
        justifyContent: "space-evenly",
    },
    wordlist: {
        height: 200,
    }

});


