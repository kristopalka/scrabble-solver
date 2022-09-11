import {Button, StyleSheet, View} from 'react-native';
import ReactNativeZoomableView from '@openspacelabs/react-native-zoomable-view/src/ReactNativeZoomableView';
import Board from "./scrabble/Board";


export default function SummaryView(props) {
    const board = props.gameStateAndBestWords.gameState.board
    const holder = props.gameStateAndBestWords.gameState.holder
    const bestWords = props.gameStateAndBestWords.bestWords

    return (
        <View style={styles.container}>
            <ReactNativeZoomableView
                contentWidth={550} contentHeight={550}
                maxZoom={4} minZoom={0.7}
                initialZoom={1}>

                <Board content={board} editMode={false}/>
            </ReactNativeZoomableView>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        height: "100%",
        width: "100%",
    },
});


