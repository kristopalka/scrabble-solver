import {StyleSheet, View} from 'react-native';
import ReactNativeZoomableView from '@openspacelabs/react-native-zoomable-view/src/ReactNativeZoomableView';
import Board from "./scrabble/Board";
import {useState} from "react";
import Holder from "./scrabble/Holder";
import CustomButton from "./other/CustomButton";
import SegmentedControl from "./other/SegmentedControll";


export default function EditBoardPage(props) {
    const [board, updateBoard] = useState(props.board);
    const [holder, updateHolder] = useState(props.holder);

    async function applyBoard() {
        props.switchToSummary(board, holder);
    }

    return (
        <View style={styles.container}>
            <ReactNativeZoomableView contentWidth={550} contentHeight={550} maxZoom={4} minZoom={0.7} initialZoom={1}>
                <Board content={board} editMode={true} updateContent={updateBoard} lettersValues={props.lettersValues}/>
            </ReactNativeZoomableView>


            <View style={styles.edit}>
                <Holder content={holder}
                        editMode={true}
                        updateContent={updateHolder}
                        lettersValues={props.lettersValues}
                />

                <SegmentedControl
                    tabs={props.modes}
                    currentIndex={props.modeIndex}
                    onChange={props.setModeIndex}
                    width={150}
                    paddingVertical={10}
                />

                <View style={styles.buttons}>
                    <CustomButton title={"Apply"} style={styles.button} onPress={applyBoard}></CustomButton>
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



