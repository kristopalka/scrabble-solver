import {Button, StyleSheet, View} from 'react-native';
import ReactNativeZoomableView from '@openspacelabs/react-native-zoomable-view/src/ReactNativeZoomableView';
import Board from "./board/Board";
import {useState} from "react";
import {exampleBoard} from "../javascript/scrabble";


export default function SummaryView(props) {
    const [content, updateContent] = useState(exampleBoard);

    return (
        <View style={styles.container}>
            <ReactNativeZoomableView
                contentWidth={550} contentHeight={550}
                maxZoom={4} minZoom={0.7}
                initialZoom={1}>

                <Board content={content} onUpdateContent={updateContent}/>

            </ReactNativeZoomableView>
            <Button title={"OK"} onPress={() => {
                console.log(content)
            }}></Button>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        height: "100%",
        width: "100%",
    },
});


