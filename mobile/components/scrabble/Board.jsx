import {StyleSheet, View} from 'react-native';
import Field from "./Field";
import {boardToString} from "../../javascript/scrabble";


export default function Board(props) {
    const fieldSize = 30;

    function updateLetter(x, y, newLetter) {
        const content = props.content;
        content[x][y] = newLetter;
        props.updateContent(content);
        console.log(boardToString(props.content))
    }

    return (
        <View style={styles.grid(fieldSize)}>
            {props.content.map((row, x) => {
                return <View style={styles.column} key={`c-${x}`}>{
                    row.map((field, y) => {
                        return <View style={styles.element} key={`r-${y}`}>
                            <Field
                                size={fieldSize}
                                input={field}
                                editMode={props.editMode}
                                updateLetter={(newLetter) => updateLetter(x, y, newLetter)}
                            />
                        </View>
                    })
                }</View>
            })}
        </View>
    );
}

const styles = StyleSheet.create({
    grid: (fieldSize) => ({
        backgroundColor: "white",
        width: (fieldSize + 2) * 15 ,
        height: (fieldSize + 2) * 15 ,
        borderWidth: 1,
        borderColor: "black",
        borderRadius: 8,
        padding: 2,
    }),
    column: {
        flex: 1,
        flexDirection: "row",
        justifyContent: "center",
    },
    element: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
    },
});
