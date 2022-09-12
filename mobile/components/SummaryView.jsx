import {Button, FlatList, StyleSheet, Text, View} from 'react-native';
import ReactNativeZoomableView from '@openspacelabs/react-native-zoomable-view/src/ReactNativeZoomableView';
import Board from "./scrabble/Board";
import Holder from "./scrabble/Holder";
import CustomButton from "./utils/CustomButton";


export default function SummaryView(props) {
    const board = props.board
    const holder = props.holder
    const bestWords = props.bestWords

    return (
        <View style={styles.container}>
            <ReactNativeZoomableView
                contentWidth={550} contentHeight={550}
                maxZoom={4} minZoom={0.7}
                initialZoom={1}>

                <Board content={board} editMode={false}/>
            </ReactNativeZoomableView>

            <View style={styles.edit}>
                <Holder content={holder} editMode={false} />
                <View style={styles.buttons}>
                    <FlatList data={bestWords} renderItem={({item}) => <Text style={styles.item}>{item.value}</Text>}/>
                </View>
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        height: "100%",
        width: "100%",
    },
    edit: {
        backgroundColor: "white",
        padding: 20,
        borderTopWidth: 1,
        borderColor: "black",
    },
    words: {
        flexDirection: "row",
        justifyContent: "space-evenly",
    },
});


