import {StyleSheet, View} from 'react-native';

import Board from "./scrabble/Board";
import {useState} from "react";
import Rack from "./scrabble/Rack";
import SegmentedControl from "./other/SegmentedControll";
import Break from "./other/Break";
import Navigation from "./other/Navigation";


export default function EditBoardPage(props) {
    const [board, updateBoard] = useState(props.board);
    const [rack, updateRack] = useState(props.rack);
    const [modeIndex, setModeIndex] = useState(props.modeIndex);

    async function applyBoard() {
        props.goSummary(board, rack, modeIndex);
    }


    return (
        <View style={styles.container}>
            <Board
                content={board} editMode={true}
                updateContent={updateBoard}
                lettersValues={props.lettersValues}
            />

            <View style={styles.edit}>
                <Rack
                    content={rack}
                    editMode={true}
                    updateContent={updateRack}
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

                <Navigation
                    onLeftClick={props.goCamera}
                    onRightClick={applyBoard}
                    helpTitle={"Edit board"}
                    helpMessage={"Correct letters on board if necessary and fill rack"}
                />
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



