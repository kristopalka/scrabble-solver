import {BackHandler, StyleSheet, View} from 'react-native';
import {useEffect, useState} from "react";
import CameraPage from "./components/CameraPage";
import EditBoardPage from "./components/EditBoardPage";
import SummaryPage from "./components/SummaryPage";
import {defaultCorners, emptyBoard, emptyRack, ScrabbleLettersValues} from "./javascript/scrabble";
import {logger, loggerErr} from "./javascript/logger";
import {
    networkFailed,
    requestCropAndRecognize,
    requestFindCorners,
    requestInfo,
    requestSolveScrabble
} from "./javascript/api";
import exampleImage from "./javascript/exampleImage"
import LoadingPage from "./components/LoadingPage";
import ErrorPage from "./components/ErrorPage";
import UrlErrorPage from "./components/UrlErrorPage";
import EditCornersPage from "./components/EditCornersPage";


const pages = {
    camera: "camera",
    editCorners: "edit-points",
    editBoard: "edit-boards",
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

    const [photo, setPhoto] = useState(exampleImage);
    const [corners, setCorners] = useState(defaultCorners);
    const [board, setBoard] = useState(emptyBoard);
    const [rack, setRack] = useState(emptyRack);
    const [words, setWords] = useState([]);

    const [url, setUrl] = useState("http://192.168.1.11:8080");

    useEffect(() => {
        const loadSettings = async () => {
            return await requestInfo(url);
        }

        logger("Fetching settings...")
        loadSettings()
            .then((newSettings) => {
                setSettings(newSettings);
                goPage(pages.camera);
                logger("Finished");
            })
            .catch((e) => {
                goPage(pages.urlError)
                loggerErr(e)
            });
    }, [])

    function applyNewUrl(newUrl, newSettings) {
        setUrl(newUrl);
        setSettings(newSettings);
        goPage(pages.camera);
    }

    function defaultErrorHandling(e) {
        switch(e) {
            case networkFailed:
                goPage(pages.urlError);
                break;
            default:
                goPage(pages.error);
        }
    }

    async function switchCameraToEditCorners(newPhoto, newLangIndex) {
        goPage(pages.loading)
        try {
            setPhoto(newPhoto);
            setLangIndex(newLangIndex);
            setCorners(await requestFindCorners(url, newPhoto))

            goPage(pages.editCorners)
        } catch (e) {
            loggerErr(e);
            defaultErrorHandling(e);
        }
    }


    async function switchEditCornersToEditBoard(newCorners) {
        goPage(pages.loading);
        try {
            setCorners(newCorners);
            setBoard(await requestCropAndRecognize(url, photo, newCorners, settings.langs[langIndex]));

            goPage(pages.editBoard)
        } catch (e) {
            loggerErr(e);
            defaultErrorHandling(e);
        }
    }

    async function switchEditBoardToSummary(newBoard, newRack, newModeIndex) {
        goPage(pages.loading);
        try {
            setBoard(newBoard);
            setRack(newRack);
            setModeIndex(newModeIndex);
            let newWords = await requestSolveScrabble(url, board, rack, settings.langs[langIndex], settings.modes[modeIndex], "15");
            setWords(newWords);

            goPage(pages.summary);
        } catch (e) {
            loggerErr(e);
            defaultErrorHandling(e);
        }
    }


    function backAction() {
        switch(page) {
            case pages.camera:
                return false;
            case pages.editBoard:
                goPage(pages.camera);
                return true;
            case pages.summary:
                goPage(pages.editBoard);
                return true;
            default:
                goPage(pages.camera);
                return true;
        }
    }

    function currentView() {
        switch(page) {
            case pages.camera:
                return <CameraPage
                    langs={settings.langs}
                    langIndex={langIndex}
                    goEditCorners={switchCameraToEditCorners}
                    goEditBoard={() => goPage(pages.editBoard)}
                />;
            case pages.editCorners:
                return <EditCornersPage
                    photo={photo}
                    corners={corners}
                    goEditBoard={switchEditCornersToEditBoard}
                    goCamera={() => goPage(pages.camera)}
                />
            case pages.editBoard:
                return <EditBoardPage
                    board={board} rack={rack}
                    lettersValues={new ScrabbleLettersValues(settings, langIndex)}
                    modes={settings.modes} modeIndex={modeIndex}
                    goSummary={switchEditBoardToSummary}
                    goCamera={() => goPage(pages.camera)}
                />;
            case pages.summary:
                return <SummaryPage
                    board={board} rack={rack} words={words}
                    lettersValues={new ScrabbleLettersValues(settings, langIndex)}
                    goEditBoards={() => goPage(pages.editBoard)}
                    goCamera={() => goPage(pages.camera)}
                />;
            case pages.loading:
                return <LoadingPage/>;
            case pages.urlError:
                return <UrlErrorPage
                    applyUrl={applyNewUrl} url={url}
                />
            case pages.error:
                return <ErrorPage
                    text={"Some error occurs"}
                    additionalText={""}
                    goCamera={() => goPage(pages.camera)}/>
            default:
                return <ErrorPage
                    text={"Page does not exist"}
                    goCamera={() => goPage(pages.camera)}
                />;
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
