import {BackHandler, StyleSheet, View} from 'react-native';
import {useState} from "react";
import CameraPage from "./components/CameraPage";
import EditBoardPage from "./components/EditBoardPage";
import SummaryPage from "./components/SummaryPage";
import {exampleBestWords, exampleBoard, exampleHolder} from "./javascript/scrabble";
import {logger} from "./javascript/logger";
import {requestImageToText, requestSolveScrabble} from "./javascript/api";
import LoadingPage from "./components/LoadingPage";
import ErrorPage from "./components/ErrorPage";

const pages = {
    camera: "camera",
    edit: "edit",
    summary: "summary",
    loading: "loading",
    error: "error"
}


export default function App() {
    BackHandler.addEventListener("hardwareBackPress", backAction);

    const [page, goPage] = useState(pages.error)

    const [board, setBoard] = useState(exampleBoard)
    const [holder, setHolder] = useState(exampleHolder)
    const [words, setWords] = useState(exampleBestWords)


    async function switchEditToSummary(board, holder) {
        logger("Solving in backend");
        goPage(pages.loading);
        const bestWords = await requestSolveScrabble(board, holder, "pl", "score", "5");
        logger("Solving OK");

        setBoard(board);
        setHolder(holder);
        setWords(bestWords);
        goPage(pages.summary)
    }

    async function switchCameraToEdit(photoBase64) {
        logger("Sending to backend");
        goPage(pages.loading);
        try {
            const board = await requestImageToText(photoBase64)
            setBoard(board);
            goPage(pages.edit);
        } catch (e) {
            logger("Error: " + e);
            goPage(pages.error);
        }
    }


    function backAction() {
        switch(page) {
            case pages.camera:
                return false;
            case pages.edit:
                goPage(pages.camera);
                return true;
            case pages.summary:
                goPage(pages.edit);
                return true;
            default:
                goPage(pages.camera);
                return true;
        }
    }

    function currentView() {
        switch(page) {
            case pages.camera:
                return <CameraPage switchToEdit={switchCameraToEdit}/>;
            case pages.edit:
                return <EditBoardPage switchToSummary={switchEditToSummary} board={board} holder={holder}/>;
            case pages.summary:
                return <SummaryPage board={board} holder={holder} words={words}/>;
            case pages.loading:
                return <LoadingPage/>;
            case pages.error:
                return <ErrorPage cause={"Some error occurs"} onClick={() => goPage(pages.camera)}/>
            default:
                return <ErrorPage cause={"Page does not exist"} onClick={() => goPage(pages.camera)}/>;
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
