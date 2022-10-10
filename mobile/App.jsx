import {BackHandler, StyleSheet, View} from 'react-native';
import {useEffect, useState} from "react";
import CameraPage from "./components/CameraPage";
import EditBoardPage from "./components/EditBoardPage";
import SummaryPage from "./components/SummaryPage";
import {emptyBoard, emptyHolder, ScrabbleLettersValues} from "./javascript/scrabble";
import {logger, loggerErr} from "./javascript/logger";
import {
    networkFailed,
    notFoundBoard,
    notFoundWords,
    requestImageToText,
    requestInfo,
    requestSolveScrabble
} from "./javascript/api";
import LoadingPage from "./components/LoadingPage";
import ErrorPage from "./components/ErrorPage";
import UrlErrorPage from "./components/UrlErrorPage";


const pages = {
    camera: "camera",
    edit: "edit",
    summary: "summary",
    loading: "loading",
    error: "error",
    urlError: "url-error",
    notFoundBoardError: "not-found-board",
    notFoundWordsError: "not-found-words",
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

    async function switchCameraToEdit(photoBase64, newLangIndex) {
        logger("Processing photo in backend");
        goPage(pages.loading);
        try {
            setLangIndex(newLangIndex);
            setBoard(await requestImageToText(url, photoBase64, settings.langs[langIndex]));

            goPage(pages.edit);
        } catch (e) {
            loggerErr(e);
            switch(e) {
                case notFoundBoard:
                    goPage(pages.notFoundBoardError);
                    break;
                case networkFailed:
                    goPage(pages.urlError);
                    break;
                default:
                    goPage(pages.error);
            }
        }
    }

    async function switchEditToSummary(newBoard, newHolder, newModeIndex) {
        logger("Solving in backend");
        goPage(pages.loading);
        try {
            setBoard(newBoard);
            setHolder(newHolder);
            setModeIndex(newModeIndex);
            let newWords = await requestSolveScrabble(url, board, holder, settings.langs[langIndex], settings.modes[modeIndex], "15");
            setWords(newWords);

            goPage(pages.summary);
        } catch (e) {
            loggerErr(e);
            switch(e) {
                case notFoundWords:
                    goPage(pages.notFoundWordsError);
                    break;
                case networkFailed:
                    goPage(pages.urlError);
                    break;
                default:
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
            case pages.notFoundWordsError:
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
                                   langIndex={langIndex}/>;
            case pages.edit:
                return <EditBoardPage switchToSummary={switchEditToSummary}
                                      board={board} holder={holder}
                                      lettersValues={new ScrabbleLettersValues(settings, langIndex)}
                                      modes={settings.modes}
                                      modeIndex={modeIndex}/>;
            case pages.summary:
                return <SummaryPage board={board} holder={holder} words={words}
                                    lettersValues={new ScrabbleLettersValues(settings, langIndex)}/>;
            case pages.loading:
                return <LoadingPage/>;
            case pages.urlError:
                return <UrlErrorPage applyUrl={applyNewUrl} url={url}/>
            case pages.notFoundBoardError:
                return <ErrorPage text={"Not found board on photo"}
                                  additionalText={"Make sure the board is on a flat surface, right in the lens. Take a picture perpendicular to the surface. "}
                                  onClick={() => goPage(pages.camera)}/>
            case pages.notFoundWordsError:
                return <ErrorPage text={"Not found words to arrange"}
                                  additionalText={"Make sure your board and holder is filled in correctly. If so, just replace the letter in your holder."}
                                  onClick={() => goPage(pages.edit)}/>
            case pages.error:
                return <ErrorPage text={"Some error occurs"}
                                  onClick={() => goPage(pages.camera)}/>
            default:
                return <ErrorPage text={"Page does not exist"}
                                  onClick={() => goPage(pages.camera)}/>;
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
