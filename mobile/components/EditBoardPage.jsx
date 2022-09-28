import {StyleSheet, View} from 'react-native';

import Board from "./scrabble/Board";
import {useState} from "react";
import Holder from "./scrabble/Holder";
import CustomButton from "./other/CustomButton";
import SegmentedControl from "./other/SegmentedControll";
import Break from "./other/Break";


export default function EditBoardPage(props) {
    const [board, updateBoard] = useState(props.board);
    const [holder, updateHolder] = useState(props.holder);
    const [modeIndex, setModeIndex] = useState(props.modeIndex);

    async function applyBoard() {
        props.switchToSummary(board, holder, modeIndex);
    }

    return (
        <View style={styles.container}>

            <Board content={board} editMode={true} updateContent={updateBoard} lettersValues={props.lettersValues}/>



            <View style={styles.edit}>
                <Holder
                    content={holder}
                    editMode={true}
                    updateContent={updateHolder}
                    lettersValues={props.lettersValues}
                />
                <Break/>

                <SegmentedControl
                    tabs={props.modes}
                    currentIndex={modeIndex}
                    onChange={setModeIndex}
                    width={200}
                    paddingVertical={10}
                    segmentedControlBackgroundColor={"white"}
                    activeSegmentBackgroundColor={"black"}
                    textColor={"black"}
                    activeTextColor={"white"}
                />
                <Break/>

                <View style={styles.buttons}>
                    <CustomButton title={"APPLY"} style={styles.button} onPress={applyBoard}></CustomButton>
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
        alignItems: "center",
        borderColor: "black",
    },
    buttons: {
        flexDirection: "row",
        justifyContent: "space-evenly",
    },
    button: {
        flex: 1,
    }
});



