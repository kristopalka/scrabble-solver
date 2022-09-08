import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import CameraView from "./components/camera/CameraView";
import BoardView from "./components/board/BoardView";

export default function App() {
  return (
    <View style={styles.container}>
      <BoardView/>
      <StatusBar style="auto" />
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
