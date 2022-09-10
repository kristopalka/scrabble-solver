import {StyleSheet, TextInput, View} from 'react-native';
import ReactNativeZoomableView from '@openspacelabs/react-native-zoomable-view/src/ReactNativeZoomableView';
import Board from "./board/Board";
import {useState} from "react";
import CustomButton from "./utils/CustomButton";
import {maxHolderSize} from "../javascript/scrabble";


export default function EditBoardView(props) {
    const [board, updateBoard] = useState(props.board);
    const [holder, updateHolder] = useState("");


    function applyBoard() {
        console.log("apply")
    }


    return (
        <View style={styles.container}>
            <ReactNativeZoomableView
                contentWidth={550} contentHeight={550}
                maxZoom={4} minZoom={0.7}
                initialZoom={1}>
                <Board content={board} onUpdateContent={updateBoard}/>
            </ReactNativeZoomableView>

            <View style={styles.edit}>
                <TextInput
                    style={styles.holderInput}
                    value={holder}
                    onChangeText={updateHolder}
                    placeholder=""
                    keyboardType="default"
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
        borderColor: "gray",
    },
    holderInput: {
        textAlign: "center",
        textAlignVertical: "center",
        includeFontPadding: false,
        fontWeight: "bold",
        fontSize: 22,
        marginBottom: 10,
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



