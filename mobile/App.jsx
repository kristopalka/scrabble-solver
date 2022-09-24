import {BackHandler, StyleSheet, View} from 'react-native';
import {useState} from "react";
import CameraView from "./components/CameraView";
import EditView from "./components/EditBoardView";
import SummaryView from "./components/SummaryView";
import {exampleBestWords, exampleBoard, exampleHolder} from "./javascript/scrabble";
import {logger} from "./javascript/logger";
import {requestImageToText, solveScrabble} from "./javascript/api";
import LoadingView from "./components/LoadingView";


export default function App() {
    const [view, changeView] = useState("camera")
    const [board, setBoard] = useState(exampleBoard)
    const [holder, setHolder] = useState(exampleHolder)
    const [words, setWords] = useState(exampleBestWords)
    const backHandler = BackHandler.addEventListener("hardwareBackPress", backAction);


    async function switchEditToSummary(board, holder) {
        logger("Solving in backend");
        changeView("loading");
        const bestWords = await solveScrabble({"board": board, "holder": holder, "number": 10, "lang": "pl"});
        logger("Solving OK");

        setBoard(board);
        setHolder(holder);
        setWords(bestWords);
        changeView("summary")
    }

    async function switchCameraToEdit(photoBase64) {
        logger("Sending to backend");
        changeView("loading");
        const output = await requestImageToText(photoBase64)

        if (output === "ERROR") {
            logger("Error while processing photo");
        } else {
            logger("Processing OK");
            setBoard(board);
            changeView("edit");
        }
    }


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

    function currentView() {
        switch(view) {
            case "camera":
                return <CameraView switchToEdit={switchCameraToEdit}/>;
            case "edit":
                return <EditView switchToSummary={switchEditToSummary} board={board} holder={holder}/>;
            case "summary":
                return <SummaryView board={board} holder={holder} words={words}/>;
            case "loading":
                return <LoadingView/>;
            default:
                return <View/>;
        }
    }

    return (<View style={styles.container}>{currentView()}</View>);
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
