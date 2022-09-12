import {StyleSheet, View} from 'react-native';
import ReactNativeZoomableView from '@openspacelabs/react-native-zoomable-view/src/ReactNativeZoomableView';
import Board from "./scrabble/Board";
import {useState} from "react";
import CustomButton from "./utils/CustomButton";
import {solveScrabble} from "../javascript/api";
import {logger} from "../javascript/logger";
import Holder from "./scrabble/Holder";


export default function EditBoardView(props) {
    const [board, updateBoard] = useState(props.board);
    const [holder, updateHolder] = useState(props.holder);

    async function applyBoard() {
        logger("Solving in backend");
        const bestWords = await solveScrabble({"board": board, "holder": holder});
        logger("Solving OK");

        props.goToSummaryView(board, holder, bestWords);
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
                <Holder content={holder} editMode={true} onUpdateContent={updateHolder}/>
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
    buttons: {
        flexDirection: "row",
        justifyContent: "space-evenly",
    },
    button: {
        margin: 10,
        flex: 1,
    }
});



