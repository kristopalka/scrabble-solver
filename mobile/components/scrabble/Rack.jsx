import {StyleSheet, View} from 'react-native';
import Field from "./Field";

export default function Rack(props) {
    const fieldSize = 44;

    function updateLetter(x, newLetter) {
        const content = props.content;
        content[x] = newLetter;
        props.updateContent(content);
    }

    return (
        <View style={styles.grid(fieldSize)}>
            {props.content.map((field, x) => {
                return <View style={styles.element} key={x}>
                    <Field
                        size={fieldSize}
                        input={field}
                        editMode={props.editMode}
                        updateLetter={(newLetter) => updateLetter(x, newLetter)}
                        lettersValues={props.lettersValues}
                    />
                </View>
            })}
        </View>
    );
}

const styles = StyleSheet.create({
    grid: (fieldSize) => ({
        backgroundColor: "white",
        flexDirection: "row",
        borderWidth: (fieldSize / 32),
        borderColor: "black",
        borderRadius: 8 * (fieldSize / 32),
        paddingVertical: 4,
        paddingHorizontal: 2,
        alignSelf: "center",
    }),
    element: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
    },
});
