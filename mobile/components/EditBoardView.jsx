import {StyleSheet, TextInput, View} from 'react-native';
import ReactNativeZoomableView from '@openspacelabs/react-native-zoomable-view/src/ReactNativeZoomableView';
import Board from "./board/Board";
import {useState} from "react";
import CustomButton from "./utils/CustomButton";
import {exampleBoard, maxHolderSize} from "../javascript/scrabble";
import {solveScrabble} from "../javascript/api";
import {logger} from "../javascript/logger";


export default function EditBoardView(props) {
    const [board, updateBoard] = useState(exampleBoard); //todo change for props.board
    const [holder, updateHolder] = useState("");

    function applyBoard() {
        logger("Applying changes")
        const gameState = {"board": board, "holder": holder}
        const bestWords = solveScrabble(gameState);

        props.goToSummaryView({"gameState": gameState, "bestWords": bestWords});
    }

    async function holderTextChange(text) {
        await updateHolder('');
        const onlyLetters = text.replace(/[^a-zA-Z]/gi, '')
        await updateHolder(onlyLetters.toUpperCase());
    }


    return (
        <View style={styles.container}>
            <ReactNativeZoomableView
                contentWidth={550} contentHeight={550}
                maxZoom={4} minZoom={0.7}
                initialZoom={1}>
                <Board content={board} editMode={true} onUpdateContent={updateBoard}/>
            </ReactNativeZoomableView>

            <View style={styles.edit}>
                <TextInput
                    value={holder}
                    autoCapitalize="characters"
                    style={styles.holderInput}
                    onChangeText={holderTextChange}
                    placeholder="HOLDER"
                    maxLength={maxHolderSize}
                />
                <View style={styles.buttons}>
                    <CustomButton title={"Cancel"} style={styles.button} onPress={props.goToCameraView}></CustomButton>
                    <CustomButton title={"Ok"} style={styles.button} onPress={applyBoard}></CustomButton>
                </View>
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        height: "100%",
        width: "100%",
    },
    edit: {
        backgroundColor: "white",
        padding: 20,
        borderTopWidth: 1,
        borderColor: "black",
    },
    holderInput: {
        letterSpacing: 5,
        textAlign: "center",
        textAlignVertical: "center",
        includeFontPadding: false,
        fontWeight: "bold",
        fontSize: 22,
        margin: 10,
        borderWidth: 1,
        borderColor: "gray",
        height: 50,
    },
    buttons: {
        flexDirection: "row",
        justifyContent: "space-evenly",
    },
    button: {
        margin: 10,
        flex: 1,
    }
});



