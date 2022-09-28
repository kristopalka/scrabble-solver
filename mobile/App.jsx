import {BackHandler, StyleSheet, View} from 'react-native';
import {useEffect, useState} from "react";
import CameraPage from "./components/CameraPage";
import EditBoardPage from "./components/EditBoardPage";
import SummaryPage from "./components/SummaryPage";
import {emptyBoard, emptyHolder, ScrabbleLettersValues} from "./javascript/scrabble";
import {logger, loggerErr} from "./javascript/logger";
import {notFoundBoard, requestImageToText, requestInfo, requestSolveScrabble} from "./javascript/api";
import LoadingPage from "./components/LoadingPage";
import ErrorPage from "./components/ErrorPage";
import BackendUrlPage from "./components/BackendUrlPage";


const pages = {
    camera: "camera",
    edit: "edit",
    summary: "summary",
    loading: "loading",
    error: "error",
    urlError: "url-error",
}


export default function App() {
    BackHandler.addEventListener("hardwareBackPress", backAction);

    const [page, goPage] = useState(pages.loading);
    const [settings, setSettings] = useState(null);
    const [langIndex, setLangIndex] = useState(0);
    const [modeIndex, setModeIndex] = useState(0);

    const [board, setBoard] = useState(emptyBoard);
    const [holder, setHolder] = useState(emptyHolder);
    const [words, setWords] = useState([]);

    let url = "http://192.168.0.139:8080";

    useEffect(() => {
        const loadSettings = async () => {
            return await requestInfo(url);
        }

        logger("Fetching settings...")
        loadSettings()
            .then((result) => {
                setSettings(result);
                goPage(pages.camera);
                logger("Finished");
            })
            .catch((e) => {
                goPage(pages.urlError)
                loggerErr(e)
            });
    }, [])


    function applyNewUrl(newUrl) {
        url = newUrl;
        goPage(pages.camera);
    }

    async function switchEditToSummary(newBoard, newHolder) {
        logger("Solving in backend");
        goPage(pages.loading);
        try {
            setBoard(newBoard);
            setHolder(newHolder);
            setWords(await requestSolveScrabble(url, board, holder,
                settings.langs[langIndex], settings.modes[modeIndex], "5"));

            goPage(pages.summary);
        } catch (e) {
            loggerErr(e);
            goPage(pages.error);
        }
    }

    async function switchCameraToEdit(photoBase64) {
        logger("Sending to backend");
        goPage(pages.loading);
        try {
            setBoard(await requestImageToText(url, photoBase64, settings.langs[langIndex]));

            goPage(pages.edit);
        } catch (e) {
            if (e === notFoundBoard) {
                loggerErr("not found board");
                goPage(pages.camera);
            } else {
                loggerErr(e);
                goPage(pages.error);
            }
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
                return <CameraPage switchToEdit={switchCameraToEdit}
                                   langs={settings.langs}
                                   langIndex={langIndex}
                                   setLangIndex={setLangIndex}/>;
            case pages.edit:
                return <EditBoardPage switchToSummary={switchEditToSummary}
                                      board={board} holder={holder}
                                      lettersValues={new ScrabbleLettersValues(settings, langIndex)}
                                      modes={settings.modes}
                                      modeIndex={modeIndex}
                                      setModeIndex={setModeIndex}/>;
            case pages.summary:
                return <SummaryPage board={board} holder={holder} words={words}
                                    lettersValues={new ScrabbleLettersValues(settings, langIndex)}/>;
            case pages.loading:
                return <LoadingPage/>;
            case pages.urlError:
                return <BackendUrlPage applyUrl={applyNewUrl} url={url}/>
            case pages.error:
                return <ErrorPage cause={"Some error occurs"} onClick={() => goPage(pages.camera)}/>
            default:
                return <ErrorPage cause={"Page does not exist"} onClick={() => goPage(pages.camera)}/>;
        }
    }

    return (
        <View style={styles.container}>
            {currentView()}
        </View>);
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
