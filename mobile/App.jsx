import {BackHandler, StyleSheet, View} from 'react-native';
import {useState} from "react";
import CameraPage from "./components/CameraPage";
import EditBoardPage from "./components/EditBoardPage";
import SummaryPage from "./components/SummaryPage";
import {exampleBestWords, exampleBoard, exampleHolder} from "./javascript/scrabble";
import {logger} from "./javascript/logger";
import {requestImageToText, requestSolveScrabble} from "./javascript/api";
import LoadingPage from "./components/LoadingPage";


export default function App() {
    BackHandler.addEventListener("hardwareBackPress", backAction);

    const [page, changePage] = useState("camera")

    const [board, setBoard] = useState(exampleBoard)
    const [holder, setHolder] = useState(exampleHolder)
    const [words, setWords] = useState(exampleBestWords)


    async function switchEditToSummary(board, holder) {
        logger("Solving in backend");
        changePage("loading");
        const bestWords = await requestSolveScrabble(board, holder, "pl", "score", "5");
        logger("Solving OK");

        setBoard(board);
        setHolder(holder);
        setWords(bestWords);
        changePage("summary")
    }

    async function switchCameraToEdit(photoBase64) {
        logger("Sending to backend");
        changePage("loading");
        try {
            const board = await requestImageToText(photoBase64)
            setBoard(board);
            changePage("edit");
        } catch (e) {
            logger("Error: " + e);
        }
    }


    function backAction() {
        switch(page) {
            case "camera":
                return false;
            case "edit":
                changePage("camera");
                return true;
            case "summary":
                changePage("edit");
                return true;
            default:
                changePage("camera");
                return true;
        }
    }

    function currentView() {
        switch(page) {
            case "camera":
                return <CameraPage switchToEdit={switchCameraToEdit}/>;
            case "edit":
                return <EditBoardPage switchToSummary={switchEditToSummary} board={board} holder={holder}/>;
            case "summary":
                return <SummaryPage board={board} holder={holder} words={words}/>;
            case "loading":
                return <LoadingPage/>;
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
