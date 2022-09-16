import {BackHandler, StyleSheet, View} from 'react-native';
import {useState} from "react";
import CameraView from "./components/CameraView";
import EditView from "./components/EditBoardView";
import SummaryView from "./components/SummaryView";
import {exampleBestWords, exampleBoard, exampleHolder} from "./javascript/scrabble";

export default function App() {
    const [view, changeView] = useState("summary")
    const [board, changeBoard] = useState(exampleBoard)
    const [holder, changeHolder] = useState(exampleHolder)
    const [words, changeWords] = useState(exampleBestWords)

    const backHandler = BackHandler.addEventListener("hardwareBackPress", backAction);

    function backAction() {
        switch(view) {
            case "camera":
                return false;
            case "edit":
                changeView("camera");
                return true;
            case "summary":
                changeView("edit");
                return true;
        }
    }


    function goToSummaryView(board, holder, bestWords) {
        changeBoard(board);
        changeHolder(holder);
        changeWords(bestWords);
        changeView("summary")
    }

    function goToEditBoardView(board) {
        changeBoard(board)
        changeView("edit")
    }

    function goToCameraView(){
        changeView("camera");
    }


    function currentView() {
        switch(view) {
            case "camera":
                return <CameraView goToEditBoardView={goToEditBoardView}/>;
            case "edit":
                return <EditView goToCameraView={goToCameraView} goToSummaryView={goToSummaryView} board={board} holder={holder}/>;
            case "summary":
                return <SummaryView goToCameraView={goToCameraView} board={board} holder={holder} words={words}/>;
            default:
                return <View/>;
        }
    }

    return (
        <View style={styles.container}>
            {currentView()}
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        height: "100%",
        width: "100%",
        backgroundColor: '#000',
        alignItems: 'center',
        justifyContent: 'center',
    },
});
