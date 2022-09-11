import {StatusBar} from 'expo-status-bar';
import {StyleSheet, View} from 'react-native';
import {useState} from "react";
import CameraView from "./components/CameraView";
import EditBoardView from "./components/EditBoardView";
import SummaryView from "./components/SummaryView";

export default function App() {
    const [view, changeView] = useState("edit-scrabble") //todo change for "camera"
    const [object, changeObject] = useState(null)

    function goToSummaryView(gameStateAndBestWords) {
        changeObject(gameStateAndBestWords)
        changeView("summary")
    }

    function goToEditBoardView(board) {
        changeObject(board)
        changeView("edit-scrabble")
    }

    function goToCameraView() {
        changeView("camera");
    }

    function currentView() {
        switch (view) {
            case "camera":
                return <CameraView goToEditBoardView={goToEditBoardView}/>;
            case "edit-scrabble":
                return <EditBoardView goToCameraView={goToCameraView} goToSummaryView={goToSummaryView} board={object}/>;
            case "summary":
                return <SummaryView goToCameraView={goToCameraView} gameStateAndBestWords={object}/>;
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
